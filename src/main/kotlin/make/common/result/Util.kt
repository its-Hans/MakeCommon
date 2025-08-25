@file:Suppress("nothing_to_inline")
package make.common.result

// 外挂，。
@Suppress("unchecked_cast")
inline fun <T, R> Ok<T, *>.cast() = this as Ok<T, R>

@Suppress("unchecked_cast")
inline fun <E, R> Err<*, E>.cast() = this as Err<R, E>

inline fun unexpected(err: Any?) = Error("unexpected error: $err")

inline fun <E> Result<*, E>.cat(action: (E) -> Unit) {
    if (this is Err) action(result)
}

inline fun <T, E> Result<T, E>.thr(action: (E) -> Throwable): T {
    cat { throw action(it) }

    return (this as Ok).result
}

inline fun <T, E> Result<T, E>.unwrap() =
    thr { unexpected(it) }

inline fun <T, E> Result<T, E>.expected(message: String) =
    thr { Exception(message) }

inline fun <T, E> Result<T, E>.ignore(match: (E) -> Boolean) {
    cat { if (!match(it)) throw unexpected(it) }
}