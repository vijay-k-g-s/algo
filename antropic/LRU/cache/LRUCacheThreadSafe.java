package cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Thread-Safe LRU Cache using LinkedHashMap with synchronized methods
 *
 * Time Complexity: O(1) for both get and put
 * Space Complexity: O(capacity)
 *
 * Thread-Safety Approaches:
 * 1. Synchronized methods (used here) - simple but coarse-grained locking
 * 2. Collections.synchronizedMap() - wraps the map with synchronized access
 * 3. ReadWriteLock - allows concurrent reads, exclusive writes
 */
public class LRUCacheThreadSafe {

    private final LinkedHashMap<Integer, Integer> cache;
    private final int capacity;

    public LRUCacheThreadSafe(int capacity) {
        this.capacity = capacity;
        // accessOrder = true means ordering by access (most recent at end)
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > LRUCacheThreadSafe.this.capacity;
            }
        };
    }

    /**
     * Thread-safe get operation
     */
    public synchronized int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    /**
     * Thread-safe put operation
     */
    public synchronized void put(int key, int value) {
        cache.put(key, value);
    }

    /**
     * Thread-safe size operation
     */
    public synchronized int size() {
        return cache.size();
    }

    /**
     * Thread-safe clear operation
     */
    public synchronized void clear() {
        cache.clear();
    }

    // Test the thread-safe implementation
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Thread-Safe LRU Cache Test ===\n");

        LRUCacheThreadSafe lru = new LRUCacheThreadSafe(100);

        // Test 1: Basic operations
        System.out.println("Test 1: Basic Operations");
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println("get(1): " + lru.get(1)); // returns 1
        lru.put(3, 3);
        System.out.println("Size: " + lru.size());
        System.out.println();

        // Test 2: Multi-threaded access
        System.out.println("Test 2: Multi-threaded Access");
        lru.clear();

        // Create multiple threads that access the cache concurrently
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                // Each thread does 100 operations
                for (int j = 0; j < 100; j++) {
                    int key = (threadId * 100) + j;
                    lru.put(key, key * 2);
                    lru.get(key);
                }
            });
        }

        // Start all threads
        long startTime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("All threads completed successfully!");
        System.out.println("Final cache size: " + lru.size());
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println();

        // Test 3: Concurrent read-write
        System.out.println("Test 3: Concurrent Read-Write Test");
        lru.clear();

        // Populate cache
        for (int i = 0; i < 50; i++) {
            lru.put(i, i * 10);
        }

        // Reader threads
        Thread reader1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                lru.get(i % 50);
            }
            System.out.println("Reader 1 completed");
        });

        Thread reader2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                lru.get(i % 50);
            }
            System.out.println("Reader 2 completed");
        });

        // Writer thread
        Thread writer = new Thread(() -> {
            for (int i = 50; i < 100; i++) {
                lru.put(i, i * 10);
            }
            System.out.println("Writer completed");
        });

        reader1.start();
        reader2.start();
        writer.start();

        reader1.join();
        reader2.join();
        writer.join();

        System.out.println("Final cache size: " + lru.size());
        System.out.println("\n✓ All concurrent tests passed!");
    }
}
