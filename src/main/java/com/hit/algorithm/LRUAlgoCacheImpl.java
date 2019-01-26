package com.hit.algorithm;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRUAlgoCacheImpl<K, V> extends AbstractAlgoCache<K, V> {

    protected LinkedHashMap<K, V> m_Cache;

    public LRUAlgoCacheImpl(int capacity) {
        super(capacity);
        // setting the cache with a access-order instead of insert-order
        // meaning on each access (get/put) the element will move forward to the top of the list
        m_Cache = new LinkedHashMap<K, V>(capacity, .75f, true);
    }

    @Override
    public V getElement(K key) {
        if (m_Cache.containsKey(key)) {
            return m_Cache.get(key);
        }
        return null;
    }

    @Override
    public void removeElement(K key) {
        if (m_Cache.containsKey(key)) {
            m_Cache.remove(key);
        }
    }

    @Override
    public V putElement(K key, V value) {
        // check if we passed the threshold
        if (m_Cache.size() >= capacity) {
            // get the eldest entry from the cache
            Entry<K, V> eldestEntry = m_Cache.entrySet().iterator().next();
            m_Cache.remove(eldestEntry.getKey());
            m_Cache.put(key, value);
            return eldestEntry.getValue();
        } else {
            m_Cache.put(key, value);
            return null;
        }
    }
}
