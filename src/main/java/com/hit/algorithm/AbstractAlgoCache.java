package com.hit.algorithm;

public abstract class AbstractAlgoCache<K,V> implements IAlgoCache<K,V>
{
    protected int capacity;

    public AbstractAlgoCache(int capacity)
    {
        this.capacity = capacity;
    }

    public abstract V getElement(K key);
    public abstract void removeElement(K key);
    public abstract V putElement(K key, V value);
}
