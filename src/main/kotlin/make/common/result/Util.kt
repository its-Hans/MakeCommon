@file:Suppress("nothing_to_inline")
package make.common.result

// 外挂，。
@Suppress("unchecked_cast")
inline fun <T, R> Ok<T, *>.cast() = this as Ok<T, R>

@Suppress("unchecked_cast")
inline fun <E, R> Err<*, E>.cast() = this as Err<R, E>

inline fun <E> Err<*, E>.unexpected(): Nothing {
    throw Error("unexpected error: $result")
}

inline fun <T, E> Result<T, E>.unwrap(): T {
    when (this) {
        is Ok -> return result
        is Err -> unexpected()
    }
}

inline fun <T, E> Result<T, E>.expected(message: String): T {
    when (this) {
        is Ok -> return result
        is Err -> throw Exception(message)
    }
}

inline fun <E> Err<*, E>.ignore(match: (E) -> Boolean) {
    if (!match(result)) unexpected()
}