package linked_list;

import java.util.HashMap;
import java.util.Map;

// LeetCode 138. Copy List with Random Pointer
//
// Each node has: int val, Node next, Node random (can point to any node or null)
// Goal: Deep copy — all new nodes, next and random pointers wired correctly.
//
// Approach: HashMap (original node → clone node)
//   Pass 1 — Create all clone nodes and store in map.
//   Pass 2 — Wire next and random pointers using the map.
//
// Why two passes?
//   When wiring random pointers, the target clone may not exist yet if done in one pass.
//   Pass 1 guarantees ALL clones exist before we wire anything.
//
// Complexity:
//   Time:  O(n)
//   Space: O(n) — HashMap stores n entries

public class CopyRandomList {

    static class Node {
        int val;
        Node next;
        Node random;

        Node(int val) {
            this.val = val;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();

        // Pass 1: create all clone nodes
        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        // Pass 2: wire next and random pointers
        curr = head;
        while (curr != null) {
            map.get(curr).next   = map.get(curr.next);     // null-safe: map.get(null) = null
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);
    }

    public static void main(String[] args) {
        CopyRandomList solution = new CopyRandomList();

        // Build: 7 → 13 → 11 → 10 → 1
        // random: 7→null, 13→7, 11→1, 10→11, 1→7
        Node n1 = new Node(7);
        Node n2 = new Node(13);
        Node n3 = new Node(11);
        Node n4 = new Node(10);
        Node n5 = new Node(1);

        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5;
        n1.random = null;
        n2.random = n1;
        n3.random = n5;
        n4.random = n3;
        n5.random = n1;

        Node cloned = solution.copyRandomList(n1);

        // Verify — different references, same structure
        Node orig = n1, copy = cloned;
        while (orig != null) {
            System.out.println("val=" + copy.val
                + "  random=" + (copy.random == null ? "null" : copy.random.val)
                + "  sameRef=" + (orig == copy));          // must always be false
            orig = orig.next;
            copy = copy.next;
        }
    }
}
