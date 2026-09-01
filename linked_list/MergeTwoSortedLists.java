package linked_list;

// LeetCode 21. Merge Two Sorted Lists
//
// Approach: Iterative with a dummy head node
//   - Use a dummy node to avoid edge cases on the head.
//   - Compare l1 and l2 values, attach the smaller node to tail.next
//   - Advance the pointer of the list we just took from.
//   - When one list is exhausted, attach the remainder of the other.
//
// Complexity:
//   Time:  O(m + n)
//   Space: O(1)

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = (l1 != null) ? l1 : l2;    // attach remaining nodes

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
        System.out.println(sb);
    }

    public static void main(String[] args) {
        MergeTwoSortedLists solution = new MergeTwoSortedLists();

        print(solution.mergeTwoLists(build(1, 2, 4), build(1, 3, 4))); // 1 → 1 → 2 → 3 → 4 → 4
        print(solution.mergeTwoLists(build(), build()));                // (empty)
        print(solution.mergeTwoLists(build(), build(0)));               // 0
    }
}
