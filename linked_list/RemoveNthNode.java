package linked_list;

// LeetCode 19. Remove Nth Node From End of List
//
// Approach: Two pointers (fast and slow) with a dummy head
//   - Move fast pointer n steps ahead.
//   - Then move both fast and slow together until fast reaches the last node.
//   - slow is now just BEFORE the node to remove.
//   - Skip the target node: slow.next = slow.next.next
//
// Why dummy node?
//   Handles the edge case where the node to remove is the head
//   (e.g. list=[1], n=1). slow starts at dummy, so slow.next = head is skippable.
//
// Complexity:
//   Time:  O(n) — single pass
//   Space: O(1)

public class RemoveNthNode {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast n steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Move both until fast reaches the last node
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the nth node from end
        slow.next = slow.next.next;

        return dummy.next;
    }

    private static ListNode build(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : vals) { curr.next = new ListNode(v); curr = curr.next; }
        return dummy.next;
    }

    private static void print(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" → ");
            head = head.next;
        }
        System.out.println(sb.length() == 0 ? "(empty)" : sb);
    }

    public static void main(String[] args) {
        RemoveNthNode solution = new RemoveNthNode();

        print(solution.removeNthFromEnd(build(1, 2, 3, 4, 5), 2)); // Expected: 1 → 2 → 3 → 5
        print(solution.removeNthFromEnd(build(1), 1));              // Expected: (empty)
        print(solution.removeNthFromEnd(build(1, 2), 1));           // Expected: 1
        print(solution.removeNthFromEnd(build(1, 2), 2));           // Expected: 2
    }
}
