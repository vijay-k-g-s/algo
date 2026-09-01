package linked_list;

// LeetCode 141. Linked List Cycle
//
// Approach: Floyd's Cycle Detection — Tortoise and Hare
//   - slow pointer moves 1 step at a time
//   - fast pointer moves 2 steps at a time
//   - If there is a cycle, fast will eventually lap slow and they MUST meet.
//   - If there is no cycle, fast reaches null and we return false.
//
// Why they always meet (not skip past each other):
//   Each iteration, fast gains exactly 1 step on slow inside the cycle.
//   So the gap closes by 1 every round — they are guaranteed to land on
//   the same node, never skipping over.
//
// Complexity:
//   Time:  O(n)
//   Space: O(1) — no extra data structures, just two pointers

public class LinkedListCycleDetection {

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;           // move 1 step
            fast = fast.next.next;      // move 2 steps

            if (slow == fast) return true;  // pointers met → cycle exists
        }

        return false;                   // fast hit null → no cycle
    }

    public static void main(String[] args) {
        LinkedListCycleDetection solution = new LinkedListCycleDetection();

        // Build: 1 → 2 → 3 → 4 → 2 (cycle back to node 2)
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n2; // cycle

        System.out.println(solution.hasCycle(n1)); // Expected: true

        // Build: 1 → 2 → 3 (no cycle)
        ListNode m1 = new ListNode(1);
        ListNode m2 = new ListNode(2);
        ListNode m3 = new ListNode(3);
        m1.next = m2; m2.next = m3;

        System.out.println(solution.hasCycle(m1)); // Expected: false

        // Single node, no cycle
        System.out.println(solution.hasCycle(new ListNode(1))); // Expected: false
    }
}
