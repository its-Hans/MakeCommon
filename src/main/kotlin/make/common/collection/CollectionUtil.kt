package make.common.collection

inline fun <K, V, T> map(set: Set<T>, mapper: ((K, V) -> Unit).(T) -> Unit): MutableMap<K, V> {
    val map = HashMap<K, V>(set.size)
    for (element in set)
        mapper(map::put, element)

    return map
}

inline fun <K, V> mapValues(keys: Set<K>, mapper: (K) -> V) =
    map(keys) {
        this(it, mapper(it))
    }

fun <K, V> mapKeys(values: Set<V>, mapper: (V) -> K) =
    map(values) {
        this(mapper(it), it)
}