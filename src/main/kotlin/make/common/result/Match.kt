package make.common.result

@JvmInline
value class ResultMatch<T, E>(val result: Result<T, E>) {
    inline fun ifOk(action: (T) -> Unit) {
        if (result is Ok<*, *>) action((result as Ok<T, *>).result)
    }

    inline fun ifErr(action: (E) -> Unit) {
        if(result is Err<*, *>) action((result as Err<*, E>).result)
    }
}

inline operator fun <T, E> Result<T, E>.invoke(action: ResultMatch<T, E>.() -> Unit) = action(ResultMatch(this))