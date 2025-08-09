package make.common.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class CollectionUtil {
    public static <K, V> Map<K, V> mapValues(Set<K> keys, Function<K, V> mapper) {
        Map<K, V> map = new HashMap<>(keys.size());
        for (K key: keys) {
            map.put(key, mapper.apply(key));
        }

        return map;
    }

    public static <K, V> Map<K, V> mapKeys(Set<V> values, Function<V, K> mapper) {
        Map<K, V> map = new HashMap<>(values.size());
        for (V value: values) {
            map.put(mapper.apply(value), value);
        }

        return map;
    }
}
