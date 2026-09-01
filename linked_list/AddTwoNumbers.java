package linked_list;

// LeetCode 2. Add Two Numbers
//
// Two non-empty linked lists represent non-negative integers in REVERSE order.
// Each node contains a single digit. Add the two numbers and return the sum
// as a linked list (also in reverse order).
//
// Example: 2→4→3 represents 342
//          5→6→4 represents 465
//          Sum:  342 + 465 = 807 → 7→0→8
//
// Approach: Simulate grade-school addition digit by digit
//   - Traverse both lists simultaneously.
//   - At each step: sum = l1.val + l2.val + carry
//   - New node val = sum % 10, carry = sum / 10
//   - Continue until both lists exhausted AND carry = 0
//
// Complexity:
//   Time:  O(max(m, n))
//   Space: O(max(m, n)) — result list

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            int sum  = val1 + val2 + carry;
            carry    = sum / 10;
            int digit = sum % 10;

            curr.next = new ListNode(digit);
            curr = curr.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

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
        AddTwoNumbers solution = new AddTwoNumbers();

        // 342 + 465 = 807
        print(solution.addTwoNumbers(build(2, 4, 3), build(5, 6, 4))); // Expected: 7 → 0 → 8

        // 0 + 0 = 0
        print(solution.addTwoNumbers(build(0), build(0)));             // Expected: 0

        // 999 + 1 = 1000
        print(solution.addTwoNumbers(build(9, 9, 9), build(1)));       // Expected: 0 → 0 → 0 → 1
    }
}
