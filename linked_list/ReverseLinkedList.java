package linked_list;

// LeetCode 206. Reverse Linked List
//
// Approach: Iterative with three pointers
//   - prev: the new next of current node
//   - curr: node being processed
//   - next: saved reference before overwriting curr.next
//
// At each step: save next, point curr.next backward, advance both pointers.
//
// Complexity:
//   Time:  O(n)
//   Space: O(1)

public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;  // save next before overwriting
            curr.next = prev;           // reverse the pointer
            prev = curr;                // advance prev
            curr = next;                // advance curr
        }

        return prev;                    // prev is the new head
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
        ReverseLinkedList solution = new ReverseLinkedList();

        ListNode l1 = build(1, 2, 3, 4, 5);
        print(l1);                          // 1 → 2 → 3 → 4 → 5
        print(solution.reverseList(l1));    // 5 → 4 → 3 → 2 → 1

        ListNode l2 = build(1, 2);
        print(solution.reverseList(l2));    // 2 → 1

        ListNode l3 = build(1);
        print(solution.reverseList(l3));    // 1
    }
}
