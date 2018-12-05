package main.java.com.hit.algorithm;

import java.util.LinkedHashMap;

public class SecondChance<K, V> extends AbstractAlgoCache<K, V> {

    protected LinkedHashMap<K,V> m_Cache;
    protected LinkedHashMap<K, Boolean> m_ReferenceBit;
    protected int capcity;

    public SecondChance(int capacity) {
        super(capacity);
        m_Cache = new LinkedHashMap<K,V>();
        m_ReferenceBit = new LinkedHashMap<K, Boolean>();
    }

    @Override
    public V getElement(K key)
    {
        if (m_Cache.containsKey(key))
        {
            m_ReferenceBit.put(key, true);
            return m_Cache.get(key);
        }
        return null;
    }

    @Override
    public void removeElement(K key)
    {
        if (m_Cache.containsKey(key))
        {
           m_Cache.remove(key);
           m_ReferenceBit.remove(key);
        }
    }

    @Override
    public V putElement(K key, V value) {
        V returnedValueElement = null;
        if (! m_Cache.containsKey(key))
        {
            if (m_Cache.size() == capcity)
            {
                returnedValueElement = removeElementByPolicy();
            }
            m_Cache.put(key, value);
            m_ReferenceBit.put(key, false);
        }
        return returnedValueElement;
    }

    private V removeElementByPolicy()
    {
        K elementToRemove = m_Cache.entrySet().iterator().next().getKey();
        V value = null;

        for(K key: m_Cache.keySet())
        {
            if (m_ReferenceBit.get(key) == false)
            {
                elementToRemove = key;
                break;
            }
            else
            {
                m_ReferenceBit.put(key, false);
            }
        }

        value = m_Cache.get(elementToRemove);
        removeElement(elementToRemove);

        return value;
    }
}
