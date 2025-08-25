@file:Suppress("nothing_to_inline")
package make.common.result

// 外挂，。
@Suppress("unchecked_cast")
inline fun <T, R> Ok<T, *>.cast() = this as Ok<T, R>

@Suppress("unchecked_cast")
inline fun <E, R> Err<*, E>.cast() = this as Err<R, E>

inline fun unexpected(err: Any?) = Error("unexpected error: $err")

inline fun <T, E> Result<T, E>.cat(action: Err<T, E>.() -> Unit) {
    if (this is Err) action(this)
}

inline fun <T, E> Result<T, E>.dft(action: Err<T, E>.() -> T): T {
    if (this is Err) return action(this)

    return (this as Ok).result
}

inline fun <T, E> Result<T, E>.thr(action: Err<T, E>.() -> Throwable): T =
    dft { throw action(this) }

inline fun <T, E> Result<T, E>.unwrap() =
    thr { unexpected(result) }

inline fun <T, E> Result<T, E>.expected(message: String) =
    thr { Exception(message) }

inline fun <T, E> Result<T, E>.ignore(match: Err<T, E>.() -> Boolean) {
    cat { if (!match(this)) throw unexpected(result) }
}