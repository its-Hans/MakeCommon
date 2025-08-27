@file:Suppress("nothing_to_inline")
package make.common.result

// 外挂，。
@Suppress("unchecked_cast")
inline fun <T, E> Ok<T>.cast() = this as Result<T, E>

@Suppress("unchecked_cast")
inline fun <T, E> Err<E>.cast() = this as Result<T, E>

inline fun unexpected(err: Any?) = Error("unexpected error: $err")

inline fun <T, E> Result<T, E>.cat(action: Err<E>.() -> Unit) {
    if (this is Err) action(this)
}

inline fun <T, E> Result<T, E>.dft(action: Err<E>.() -> T): T {
    cat { return action() }

    return (this as Ok).result
}

inline fun <T, E> Result<T, E>.thr(action: Err<E>.() -> Throwable) =
    dft { throw action(this) }

inline fun <T, E> Result<T, E>.unwrap() =
    thr { unexpected(error) }

inline fun <T, E> Result<T, E>.expected(message: String) =
    thr { Exception(message) }

inline fun <T, E> Result<T, E>.ignore(match: Err<E>.() -> Boolean) {
    cat { if (!match(this)) throw unexpected(error) }
}

// 意味不明的周文轩代码。
inline fun <T, E, R> Result<T, E>.mapok(transform: (T) -> R): Result<R, E> {
    return when (this) {
        is Ok -> Ok(transform(result))
        is Err -> cast()
    }
}

inline fun <T, E, R> Result<T, E>.maper(transform: (E) -> R): Result<T, R> {
    return when (this) {
        is Err -> Err(transform(error))
        is Ok -> cast()
    }
}