package make.common.result

sealed interface Result<out T, out E> {
    val result: Any?
    val cause: Result<*, *>?
}

data class Ok<T> @JvmOverloads
constructor(
    override val result: T,
    override val cause: Result<*, *>? = null
): Result<T, Nothing>

data class Err<E> @JvmOverloads
constructor(
    val error: E,
    override val cause: Result<*, *>? = null
): Result<Nothing, E> {
    override val result: E
        get() = error
}