package make.common.collection

inline fun <K, V, T> Collection<T>.map(transformer: (T) -> Pair<K, V>): MutableMap<K, V> {
    val map = HashMap<K, V>(size)

    for (element in this)
        transformer(element).apply { map[first] = second }

    return map
}

inline fun <K, V> Collection<K>.mapValues(transformer: (K) -> V) =
    map { it to transformer(it) }

inline fun <K, V> Collection<V>.mapKeys(transformer: (V) -> K) =
    map { transformer(it) to it }