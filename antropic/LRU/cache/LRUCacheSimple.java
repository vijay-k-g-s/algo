package cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU Cache - Simple Implementation using LinkedHashMap
 *
 * This is a cleaner approach if interviewer allows using Java built-ins.
 * LinkedHashMap maintains insertion order and has removeEldestEntry hook.
 *
 * Time Complexity: O(1) for both get and put
 * Space Complexity: O(capacity)
 */
public class LRUCacheSimple {

    private final LinkedHashMap<Integer, Integer> cache;
    private final int capacity;

    public LRUCacheSimple(int capacity) {
        this.capacity = capacity;
        // accessOrder = true means ordering by access (most recent at end)
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }

    // Test the implementation
    public static void main(String[] args) {
        System.out.println("=== Simple LRU Cache Test (LinkedHashMap) ===\n");

        LRUCacheSimple lru = new LRUCacheSimple(2);

        lru.put(1, 1);
        System.out.println("put(1, 1): " + lru.cache);

        lru.put(2, 2);
        System.out.println("put(2, 2): " + lru.cache);

        System.out.println("get(1): " + lru.get(1));
        System.out.println("Cache: " + lru.cache);

        lru.put(3, 3); // evicts key 2
        System.out.println("put(3, 3): " + lru.cache);

        System.out.println("get(2): " + lru.get(2)); // returns -1

        lru.put(4, 4); // evicts key 1
        System.out.println("put(4, 4): " + lru.cache);

        System.out.println("get(1): " + lru.get(1)); // returns -1
        System.out.println("get(3): " + lru.get(3)); // returns 3
        System.out.println("get(4): " + lru.get(4)); // returns 4
    }
}
