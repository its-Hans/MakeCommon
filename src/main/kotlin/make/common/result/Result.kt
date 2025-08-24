@file:Suppress("nothing_to_inline", "unchecked_cast")
package make.common.result

sealed interface Result<out T, out E> {
    val cause: Result<*, *>?
    val result: Any?
}

class Ok<T, E>(override val result: T,
               override val cause: Result<*, *>? = null
): Result<T, E>

class Err<T, E>(override val result: E,
                override val cause: Result<*, *>? = null
): Result<T, E>


inline fun <T, E, R> Result<T, E>.map(transform: (T) -> R): Result<R, E> {
    return when (this) {
        is Ok -> Ok(transform(result))
        is Err -> this as Err<R, E> // 外挂，。
    }
}

inline fun <T, E> Result<T, E>.unwrap(): T {
    when (this) {
        is Ok -> return result
        is Err -> throw Error("result isn't ok but unwrap called")
    }
}

inline fun <T, E> Result<T, E>.expected(message: String): T {
    when (this) {
        is Ok -> return result
        is Err -> throw Error(message)
    }
}