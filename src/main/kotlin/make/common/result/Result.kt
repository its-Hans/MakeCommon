package make.common.result

sealed interface Result<out T, out E> {
    val cause: Result<*, *>?
    val result: Any?

    fun unwrap(): T
    fun expected(message: String): T
}

class Ok<T, E>(private val ok: T,
               override val cause: Result<*, *>? = null
): Result<T, E>
{
    override val result get() = ok
    override fun unwrap() = ok
    override fun expected(message: String): T = ok
}

class Err<T, E>(private val err: E,
                override val cause: Result<*, *>? = null
): Result<T, E>
{
    override val result get() = err
    override fun unwrap() = throw Error("result isn't ok but unwrap called")
    override fun expected(message: String) = throw Error(message)
}

inline fun <T, R, E> Result<T, E>.map(transform: (T) -> R): Result<R, E> {
    return when (this) {
        is Ok -> Ok(transform(this.unwrap()))
        is Err -> this
    }
}
