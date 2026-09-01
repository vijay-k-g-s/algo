package cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Robust LRU Cache Implementation with Comprehensive Input Validation
 *
 * This implementation handles:
 * - Invalid capacity (zero, negative, very large)
 * - Null handling for generic types
 * - Integer overflow scenarios
 * - Thread-safety considerations
 * - Proper exception messages
 *
 * Time Complexity: O(1) for get and put operations
 * Space Complexity: O(capacity)
 */
public class LRUCacheRobust<K, V> {

    private static final int MAX_CAPACITY = Integer.MAX_VALUE / 2; // Prevent overflow
    private static final int DEFAULT_CAPACITY = 16;

    // Node class for doubly linked list
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final Node<K, V> head; // MRU (Most Recently Used)
    private final Node<K, V> tail; // LRU (Least Recently Used)
    private int size;

    /**
     * Create LRU Cache with specified capacity
     * @param capacity - maximum number of entries
     * @throws IllegalArgumentException if capacity is invalid
     */
    public LRUCacheRobust(int capacity) {
        validateCapacity(capacity);

        this.capacity = capacity;
        this.cache = new HashMap<>((int) (capacity / 0.75f) + 1);
        this.size = 0;

        // Initialize dummy head and tail nodes
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Create LRU Cache with default capacity
     */
    public LRUCacheRobust() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Validate capacity constraints
     */
    private void validateCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                "Capacity must be positive, got: " + capacity
            );
        }
        if (capacity > MAX_CAPACITY) {
            throw new IllegalArgumentException(
                "Capacity exceeds maximum allowed (" + MAX_CAPACITY + "), got: " + capacity
            );
        }
    }

    /**
     * Get value from cache
     * @param key - the key to lookup (cannot be null)
     * @return Optional containing value if exists, empty otherwise
     * @throws IllegalArgumentException if key is null
     */
    public Optional<V> get(K key) {
        validateKey(key);

        if (!cache.containsKey(key)) {
            return Optional.empty();
        }

        Node<K, V> node = cache.get(key);
        // Move to front (most recently used)
        moveToHead(node);
        return Optional.ofNullable(node.value);
    }

    /**
     * Get value or return default if not present
     * @param key - the key to lookup
     * @param defaultValue - value to return if key not found
     * @return value if exists, defaultValue otherwise
     */
    public V getOrDefault(K key, V defaultValue) {
        if (key == null) {
            return defaultValue;
        }
        return get(key).orElse(defaultValue);
    }

    /**
     * Put key-value pair into cache
     * @param key - the key (cannot be null)
     * @param value - the value (can be null)
     * @throws IllegalArgumentException if key is null
     */
    public void put(K key, V value) {
        validateKey(key);

        if (cache.containsKey(key)) {
            // Update existing node
            Node<K, V> node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // Create new node
            Node<K, V> newNode = new Node<>(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;

            // Check capacity and evict if necessary
            if (size > capacity) {
                Node<K, V> lru = removeTail();
                if (lru != null && lru.key != null) {
                    cache.remove(lru.key);
                    size--;
                }
            }
        }
    }

    /**
     * Remove key from cache
     * @param key - the key to remove
     * @return true if key was present, false otherwise
     */
    public boolean remove(K key) {
        if (key == null || !cache.containsKey(key)) {
            return false;
        }

        Node<K, V> node = cache.get(key);
        removeNode(node);
        cache.remove(key);
        size--;
        return true;
    }

    /**
     * Check if cache contains key
     * @param key - the key to check
     * @return true if key exists, false otherwise
     */
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        return cache.containsKey(key);
    }

    /**
     * Clear all entries from cache
     */
    public void clear() {
        cache.clear();
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /**
     * Get current size of cache
     * @return number of entries currently in cache
     */
    public int size() {
        return size;
    }

    /**
     * Get capacity of cache
     * @return maximum capacity
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Check if cache is empty
     * @return true if cache has no entries
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Check if cache is full
     * @return true if cache is at capacity
     */
    public boolean isFull() {
        return size >= capacity;
    }

    /**
     * Validate key is not null
     */
    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
    }

    // Helper method: Add node right after head
    private void addToHead(Node<K, V> node) {
        if (node == null) return;

        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // Helper method: Remove node from list
    private void removeNode(Node<K, V> node) {
        if (node == null || node.prev == null || node.next == null) {
            return;
        }

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper method: Move node to head (mark as most recently used)
    private void moveToHead(Node<K, V> node) {
        if (node == null) return;

        removeNode(node);
        addToHead(node);
    }

    // Helper method: Remove and return tail node (LRU)
    private Node<K, V> removeTail() {
        Node<K, V> lru = tail.prev;
        if (lru == head) {
            return null; // Cache is empty
        }
        removeNode(lru);
        return lru;
    }

    // For testing/debugging
    public void printCache() {
        System.out.print("Cache (MRU -> LRU): ");
        Node<K, V> curr = head.next;
        while (curr != tail) {
            System.out.print("(" + curr.key + ":" + curr.value + ") ");
            curr = curr.next;
        }
        System.out.println("[size=" + size + ", capacity=" + capacity + "]");
    }

    // Comprehensive test cases
    public static void main(String[] args) {
        System.out.println("=== Robust LRU Cache Tests ===\n");

        // Test 1: Normal operation
        System.out.println("Test 1: Normal Operation");
        testNormalOperation();

        // Test 2: Edge case - capacity = 1
        System.out.println("\nTest 2: Edge Case - Capacity 1");
        testCapacityOne();

        // Test 3: Invalid capacity
        System.out.println("\nTest 3: Invalid Capacity");
        testInvalidCapacity();

        // Test 4: Null key handling
        System.out.println("\nTest 4: Null Key Handling");
        testNullKeys();

        // Test 5: Null value handling
        System.out.println("\nTest 5: Null Value Handling");
        testNullValues();

        // Test 6: Large capacity
        System.out.println("\nTest 6: Large Capacity");
        testLargeCapacity();

        // Test 7: Remove operations
        System.out.println("\nTest 7: Remove Operations");
        testRemove();

        // Test 8: Clear and isEmpty
        System.out.println("\nTest 8: Clear and isEmpty");
        testClearAndEmpty();
    }

    private static void testNormalOperation() {
        LRUCacheRobust<Integer, Integer> cache = new LRUCacheRobust<>(3);

        cache.put(1, 100);
        cache.put(2, 200);
        cache.put(3, 300);
        cache.printCache();

        System.out.println("get(2): " + cache.get(2));
        cache.put(4, 400); // Should evict key 1
        cache.printCache();

        System.out.println("get(1): " + cache.get(1)); // Should be empty
        System.out.println("Contains key 1: " + cache.containsKey(1));
    }

    private static void testCapacityOne() {
        LRUCacheRobust<String, String> cache = new LRUCacheRobust<>(1);

        cache.put("a", "alpha");
        System.out.println("get(a): " + cache.get("a"));
        cache.printCache();

        cache.put("b", "beta"); // Should evict "a"
        System.out.println("get(a): " + cache.get("a"));
        System.out.println("get(b): " + cache.get("b"));
        cache.printCache();
    }

    private static void testInvalidCapacity() {
        try {
            new LRUCacheRobust<Integer, Integer>(0);
            System.out.println("ERROR: Should have thrown exception for capacity 0");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Caught expected exception: " + e.getMessage());
        }

        try {
            new LRUCacheRobust<Integer, Integer>(-5);
            System.out.println("ERROR: Should have thrown exception for negative capacity");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Caught expected exception: " + e.getMessage());
        }

        try {
            new LRUCacheRobust<Integer, Integer>(Integer.MAX_VALUE);
            System.out.println("ERROR: Should have thrown exception for excessive capacity");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Caught expected exception: " + e.getMessage());
        }
    }

    private static void testNullKeys() {
        LRUCacheRobust<String, Integer> cache = new LRUCacheRobust<>(2);

        try {
            cache.put(null, 100);
            System.out.println("ERROR: Should have thrown exception for null key");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Caught expected exception on put: " + e.getMessage());
        }

        try {
            cache.get(null);
            System.out.println("ERROR: Should have thrown exception for null key");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Caught expected exception on get: " + e.getMessage());
        }

        // getOrDefault should handle null gracefully
        Integer result = cache.getOrDefault(null, 999);
        System.out.println("✓ getOrDefault(null, 999): " + result);
    }

    private static void testNullValues() {
        LRUCacheRobust<String, Integer> cache = new LRUCacheRobust<>(2);

        cache.put("key1", null);
        System.out.println("get(key1) with null value: " + cache.get("key1"));
        System.out.println("containsKey(key1): " + cache.containsKey("key1"));
        cache.printCache();
    }

    private static void testLargeCapacity() {
        int largeCapacity = 100000;
        LRUCacheRobust<Integer, Integer> cache = new LRUCacheRobust<>(largeCapacity);

        // Add many entries
        for (int i = 0; i < largeCapacity; i++) {
            cache.put(i, i * 10);
        }

        System.out.println("Size after adding " + largeCapacity + " entries: " + cache.size());
        System.out.println("isFull: " + cache.isFull());

        // Add one more to trigger eviction
        cache.put(largeCapacity, largeCapacity * 10);
        System.out.println("Size after one more: " + cache.size());
        System.out.println("First entry (0) still exists: " + cache.containsKey(0));
    }

    private static void testRemove() {
        LRUCacheRobust<Integer, String> cache = new LRUCacheRobust<>(3);

        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        cache.printCache();

        boolean removed = cache.remove(2);
        System.out.println("Removed key 2: " + removed);
        cache.printCache();

        boolean notRemoved = cache.remove(99);
        System.out.println("Removed non-existent key 99: " + notRemoved);

        boolean nullRemove = cache.remove(null);
        System.out.println("Removed null key: " + nullRemove);
    }

    private static void testClearAndEmpty() {
        LRUCacheRobust<String, String> cache = new LRUCacheRobust<>(3);

        System.out.println("isEmpty (new cache): " + cache.isEmpty());

        cache.put("a", "alpha");
        cache.put("b", "beta");
        System.out.println("isEmpty (after adds): " + cache.isEmpty());
        System.out.println("size: " + cache.size());
        cache.printCache();

        cache.clear();
        System.out.println("isEmpty (after clear): " + cache.isEmpty());
        System.out.println("size: " + cache.size());
        cache.printCache();
    }
}
