@file:Suppress("nothing_to_inline")
package make.common.result

class UnexpectedErr(err: Err<*>): Error("unexpected error: $err")

/** catch */
inline fun <T, E> Result<T, E>.cat(action: Err<E>.() -> Unit) {
    if (this is Err) action(this)
}

/** default */
inline fun <T, E> Result<T, E>.dft(action: Err<E>.() -> T): T {
    cat { return action() }

    return (this as Ok).result
}

/** throw */
inline fun <T, E> Result<T, E>.thr(action: Err<E>.() -> Throwable): T =
    dft { throw action(this) }

/** unwrap */
inline fun <T, E> Result<T, E>.unr(): T =
    thr { UnexpectedErr(this) }

/** expected */
inline fun <T, E> Result<T, E>.exp(message: Err<E>.() -> String): T =
    thr { Exception(message()) }

inline fun <T, E> Result<T, E>.exp(message: String): T =
    exp { message }

/** ignore */
inline fun <T, E> Result<T, E>.igr(match: Err<E>.() -> Boolean) {
    cat { if (!match(this)) throw UnexpectedErr(this) }
}

// 意味不明的周文轩代码。
inline fun <T, E, R> Result<T, E>.map(transform: (T) -> R): Result<R, E> {
    return when (this) {
        is Ok -> Ok(transform(result))
        is Err -> this
    }
}

/** map err */
inline fun <T, E, R> Result<T, E>.mae(transform: (E) -> R): Result<T, R> {
    return when (this) {
        is Err -> Err(transform(error))
        is Ok -> this
    }
}