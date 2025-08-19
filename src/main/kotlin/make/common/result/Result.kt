package make.common.result

sealed interface Result<T, E> {
    val result: Any?

    fun unwrap(): T
    fun expected(message: String): T
}

class Ok<T, E>(private val ok: T): Result<T, E> {
    override val result get() = ok
    override fun unwrap() = ok
    override fun expected(message: String): T = ok
}

class Err<T, E>(private val err: E): Result<T, E> {
    override val result get() = err
    override fun unwrap() = throw Error("result isn't ok but unwrap called")
    override fun expected(message: String) = throw Error(message)
}