package make.common.collection

inline fun <T, R> Collection<T>.map(transform: (T) -> R): MutableList<R> {
    val list = ArrayList<R>(size)
    for (element in this)
        list += transform(element)

    return list
}

inline fun <K, V, T> Collection<T>.map(transform: (T) -> Pair<K, V>): MutableMap<K, V> {
    val map = HashMap<K, V>(size)

    for (element in this)
        transform(element).apply { map[first] = second }

    return map
}

inline fun <K, V> Collection<K>.mapValues(transform: (K) -> V) =
    map { it to transform(it) }

inline fun <K, V> Collection<V>.mapKeys(transform: (V) -> K) =
    map { transform(it) to it }