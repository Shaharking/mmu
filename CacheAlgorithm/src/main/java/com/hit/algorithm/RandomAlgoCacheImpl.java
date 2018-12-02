package main.java.com.hit.algorithm;

public class RandomAlgoCacheImpl<K,V> extends AbstractAlgoCache<K,V>
{
    public RandomAlgoCacheImpl (int capacity)
    {
        super(capacity);
    }

    @Override
    public V getElement(K key) {
        return null;
    }

    @Override
    public void removeElement(K key) {

    }

    @Override
    public V putElement(K key, V value) {
        return null;
    }
}
