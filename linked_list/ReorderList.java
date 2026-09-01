package linked_list;

// LeetCode 143. Reorder List
//
// Given: L0 → L1 → ... → Ln-1 → Ln
// Reorder to: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...
//
// Approach: 3 steps
//   Step 1 — Find middle: slow/fast pointers → split list into two halves
//   Step 2 — Reverse second half
//   Step 3 — Merge two halves by interleaving
//
// Complexity:
//   Time:  O(n)
//   Space: O(1)

public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // Step 1: Find middle (slow stops at middle)
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half (slow.next is start of second half)
        ListNode second = reverse(slow.next);
        slow.next = null;                       // cut the list into two halves
        ListNode first = head;

        // Step 3: Merge two halves by interleaving
        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;

            first.next = second;
            second.next = tmp1;

            first = tmp1;
            second = tmp2;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
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
        System.out.println(sb);
    }

    public static void main(String[] args) {
        ReorderList solution = new ReorderList();

        ListNode l1 = build(1, 2, 3, 4);
        solution.reorderList(l1);
        print(l1); // Expected: 1 → 4 → 2 → 3

        ListNode l2 = build(1, 2, 3, 4, 5);
        solution.reorderList(l2);
        print(l2); // Expected: 1 → 5 → 2 → 4 → 3
    }
}
