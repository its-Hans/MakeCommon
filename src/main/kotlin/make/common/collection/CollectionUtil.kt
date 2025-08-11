package make.common.collection

inline fun <T, R> Collection<T>.map(transform: (T) -> R): MutableList<R> {
    val list = ArrayList<R>(size)
    for (element in this)
        list += transform(element)

    return list
}

fun <K, V> Map<K, V>.reverse() = map { k, v -> v to k }

inline fun <K, V, K0, V0> Map<K, V>.map(transform: (K, V) -> Pair<K0, V0>): MutableMap<K0, V0> {
    val map = HashMap<K0, V0>(size)
    for (entry in this) transform(entry.key, entry.value).apply {
        map[first] = second
    }

    return map
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