package antropic.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU Cache Implementation using HashMap + Doubly Linked List
 *
 * Time Complexity: O(1) for both get and put operations
 * Space Complexity: O(capacity)
 *
 * Interview Approach:
 * - HashMap for O(1) access to nodes
 * - Doubly Linked List for O(1) insertion/deletion
 * - Head = Most Recently Used (MRU)
 * - Tail = Least Recently Used (LRU)
 */
public class LRUCache {

    // Node class for doubly linked list
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head; // MRU (Most Recently Used)
    private final Node tail; // LRU (Least Recently Used)

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Initialize dummy head and tail nodes
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Get value from cache
     * @param key - the key to lookup
     * @return value if exists, -1 otherwise
     */
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);
        // Move to front (most recently used)
        moveToHead(node);
        return node.value;
    }

    /**
     * Put key-value pair into cache
     * @param key - the key
     * @param value - the value
     */
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Update existing node
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // Create new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);

            // Check capacity
            if (cache.size() > capacity) {
                // Remove LRU (tail)
                Node lru = removeTail();
                cache.remove(lru.key);
            }
        }
    }

    // Helper method: Add node right after head
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // Helper method: Remove node from list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper method: Move node to head (mark as most recently used)
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    // Helper method: Remove and return tail node (LRU)
    private Node removeTail() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }

    // For testing/debugging
    public void printCache() {
        System.out.print("Cache (MRU -> LRU): ");
        Node curr = head.next;
        while (curr != tail) {
            System.out.print("(" + curr.key + ":" + curr.value + ") ");
            curr = curr.next;
        }
        System.out.println();
    }

    // Test the implementation
    public static void main(String[] args) {
        System.out.println("=== LRU Cache Test ===\n");

        LRUCache lru = new LRUCache(2);

        System.out.println("put(1, 1)");
        lru.put(1, 1);
        lru.printCache();

        System.out.println("put(2, 2)");
        lru.put(2, 2);
        lru.printCache();

        System.out.println("get(1): " + lru.get(1)); // returns 1
        lru.printCache();

        System.out.println("put(3, 3)"); // evicts key 2
        lru.put(3, 3);
        lru.printCache();

        System.out.println("get(2): " + lru.get(2)); // returns -1 (not found)
        lru.printCache();

        System.out.println("put(4, 4)"); // evicts key 1
        lru.put(4, 4);
        lru.printCache();

        System.out.println("get(1): " + lru.get(1)); // returns -1 (not found)
        System.out.println("get(3): " + lru.get(3)); // returns 3
        System.out.println("get(4): " + lru.get(4)); // returns 4
        lru.printCache();
    }
}
