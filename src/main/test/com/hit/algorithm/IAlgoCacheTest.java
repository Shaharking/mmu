package com.hit.algorithm;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.AbstractMap;

public class IAlgoCacheTest {

    private static final int CAPACITY = 10;
    private IAlgoCache<Integer, String> testAlgo;


    @Test
    public void TestLRU() {
        testAlgo = new LRUAlgoCacheImpl<Integer, String>(CAPACITY);
        for (int i = 0; i < CAPACITY; i++) {
            testAlgo.putElement(i, Integer.toString(i));
        }

        String returnedValue = null;

        for (int i = 0; i < CAPACITY - 1; i++) {
            returnedValue = testAlgo.putElement(i, Integer.toString(i));
            assertEquals(Integer.toString(i), returnedValue);
        }

        returnedValue = testAlgo.putElement(20, "S");
        assertEquals("9", returnedValue);
    }


    @Test
    public void TestSecondChance() {
        testAlgo = new SecondChance<Integer, String>(CAPACITY);
        for (int i = 0; i < CAPACITY; i++) {
            testAlgo.putElement(i, Integer.toString(i));
        }
        String returnedValue = null;
        returnedValue = testAlgo.putElement(CAPACITY + 1, "Test");
        assertEquals("0", returnedValue);
        returnedValue = testAlgo.putElement(CAPACITY + 1, "Test");
        assertEquals(null, returnedValue);
    }

    @Test
    public void TestRandom() {
        testAlgo = new RandomAlgoCacheImpl<Integer, String>(CAPACITY);
        for (int i = 0; i < CAPACITY; i++) {
            testAlgo.putElement(i, Integer.toString(i));
        }

        AbstractMap.SimpleEntry<Integer, String> testEntry = new AbstractMap.SimpleEntry<>(CAPACITY, "Test");

        String returnedValue = testAlgo.putElement(testEntry.getKey(), testEntry.getValue());

        boolean isExists = false;
        for (int i = 0; i < CAPACITY; i++) {
            if (Integer.toString(i).equals(returnedValue)) {
                isExists = true;
                break;
            }
        }
        assertTrue(isExists);
    }
}
