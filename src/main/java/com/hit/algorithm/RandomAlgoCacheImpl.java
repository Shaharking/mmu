package com.hit.algorithm;

import java.util.LinkedHashMap;
import java.util.Random;

public class RandomAlgoCacheImpl<K, V> extends AbstractAlgoCache<K, V> {
    protected LinkedHashMap<K, V> m_Cache;
    private Random m_Random;

    public RandomAlgoCacheImpl(int capacity) {
        super(capacity);
        m_Cache = new LinkedHashMap<K, V>();
        m_Random = new Random();
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

        V returnedValue = null;
        if (!m_Cache.containsKey(key)) {
            if (m_Cache.size() == capacity) {
                returnedValue = removeElementByPolicy();
            }
            m_Cache.put(key, value);
        }
        return returnedValue;

    }

    private V removeElementByPolicy() {
        V returnedValue;
        K selectedKeyToDelete = m_Cache.entrySet().iterator().next().getKey();

        int item = m_Random.nextInt(capacity);
        int loop = 0;
        for (K keyLoop : m_Cache.keySet()) {
            if (loop == item) {
                selectedKeyToDelete = keyLoop;
            }
            loop++;
        }
        returnedValue = m_Cache.get(selectedKeyToDelete);
        removeElement(selectedKeyToDelete);
        return returnedValue;
    }
}
