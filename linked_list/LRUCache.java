package linked_list;

import java.util.HashMap;
import java.util.Map;

// LeetCode 146. LRU Cache
//
// Approach: HashMap + Doubly Linked List
//   - HashMap  → O(1) lookup of any node by key
//   - DLL      → O(1) insert and delete anywhere
//   - head.next = MRU (most recently used)
//   - tail.prev = LRU (least recently used)
//
// On get: move accessed node to head (mark as MRU)
// On put: add new node to head; if over capacity, evict tail.prev (LRU)
//
// Complexity:
//   Time:  O(1) for both get and put
//   Space: O(capacity)

public class LRUCache {

    private class Node {
        int key, val;
        Node prev, next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head;                        // dummy MRU sentinel
    private final Node tail;                        // dummy LRU sentinel

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        moveToHead(node);                           // mark as recently used
        return node.val;
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.val = val;
            moveToHead(node);
        } else {
            Node node = new Node(key, val);
            cache.put(key, node);
            addToHead(node);

            if (cache.size() > capacity) {
                Node lru = removeTail();            // evict least recently used
                cache.remove(lru.key);
            }
        }
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }

    private void print() {
        StringBuilder sb = new StringBuilder("MRU → ");
        Node curr = head.next;
        while (curr != tail) {
            sb.append("[").append(curr.key).append(":").append(curr.val).append("] ");
            curr = curr.next;
        }
        sb.append("← LRU");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);

        lru.put(1, 1); lru.print();                // MRU → [1:1] ← LRU
        lru.put(2, 2); lru.print();                // MRU → [2:2] [1:1] ← LRU
        System.out.println(lru.get(1));            // 1  (1 moves to MRU)
        lru.print();                               // MRU → [1:1] [2:2] ← LRU
        lru.put(3, 3); lru.print();               // MRU → [3:3] [1:1] ← LRU  (2 evicted)
        System.out.println(lru.get(2));            // -1 (evicted)
        lru.put(4, 4); lru.print();               // MRU → [4:4] [3:3] ← LRU  (1 evicted)
        System.out.println(lru.get(1));            // -1 (evicted)
        System.out.println(lru.get(3));            // 3
        System.out.println(lru.get(4));            // 4
    }
}
