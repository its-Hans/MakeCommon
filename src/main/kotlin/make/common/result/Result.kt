package make.common.result

sealed interface Result<out T, out E> {
    val cause: Result<*, *>?
}

class Ok<T> @JvmOverloads
constructor(
    val result: T,
    override val cause: Result<*, *>? = null
): Result<T, Nothing>

class Err<E> @JvmOverloads
constructor(
    val error: E,
    override val cause: Result<*, *>? = null
): Result<Nothing, E>