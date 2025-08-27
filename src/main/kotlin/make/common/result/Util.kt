@file:Suppress("nothing_to_inline")
package make.common.result

class UnexpectedErr(err: Err<*>): Error("unexpected error: $err")

inline fun <T, E> Result<T, E>.cat(action: Err<E>.() -> Unit) {
    if (this is Err) action(this)
}

inline fun <T, E> Result<T, E>.dft(action: Err<E>.() -> T): T {
    cat { return action() }

    return (this as Ok).result
}

inline fun <T, E> Result<T, E>.thr(action: Err<E>.() -> Throwable): T =
    dft { throw action(this) }

inline fun <T, E> Result<T, E>.unw(): T =
    thr { UnexpectedErr(this) }

inline fun <T, E> Result<T, E>.exp(message: Err<E>.() -> String): T =
    thr { Exception(message()) }

inline fun <T, E> Result<T, E>.exp(message: String): T =
    exp { message }

inline fun <T, E> Result<T, E>.ign(match: Err<E>.() -> Boolean) {
    cat { if (!match(this)) throw UnexpectedErr(this) }
}

// 意味不明的周文轩代码。
inline fun <T, E, R> Result<T, E>.map(transform: (T) -> R): Result<R, E> {
    return when (this) {
        is Ok -> Ok(transform(result))
        is Err -> this as Result<R, E>
    }
}

inline fun <T, E, R> Result<T, E>.mae(transform: (E) -> R): Result<T, R> {
    return when (this) {
        is Err -> Err(transform(error))
        is Ok -> this as Result<T, R>
    }
}