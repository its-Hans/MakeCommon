package make.common.result

sealed interface Result<out T, out E> {
    val result: Any?
    val cause: Result<*, *>?
}

class Ok<T, E> @JvmOverloads
constructor(
    override val result: T,
    override val cause: Result<*, *>? = null
): Result<T, E>

class Err<T, E> @JvmOverloads
constructor(
    override val result: E,
    override val cause: Result<*, *>? = null
): Result<T, E>