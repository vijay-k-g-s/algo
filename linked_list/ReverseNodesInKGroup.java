package linked_list;

// LeetCode 25. Reverse Nodes in K Group
//
// Reverse every k consecutive nodes. If remaining nodes < k, leave them as-is.
//
// Approach: Iterative group reversal
//   For each group of k nodes:
//     1. Find the kth node — if fewer than k remain, stop.
//     2. Save tail (groupPrev.next) — it becomes tail of reversed group.
//     3. Reverse k nodes, connecting the last node to groupNext.
//     4. Rewire: groupPrev.next = kth (new head), tail.next = groupNext.
//     5. Advance groupPrev to tail (end of reversed group).
//
// Complexity:
//   Time:  O(n) — each node reversed exactly once
//   Space: O(1)

public class ReverseNodesInKGroup {

    // Returns the kth node from start, or null if fewer than k nodes exist
    private ListNode getKth(ListNode start, int k) {
        while (start != null && k > 0) {
            start = start.next;
            k--;
        }
        return start;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) break;                     // fewer than k nodes remain

            ListNode groupNext = kth.next;              // node after this group
            ListNode tail = groupPrev.next;             // will become tail after reversal

            // Reverse k nodes: stop when curr reaches groupNext
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            // Rewire connections
            groupPrev.next = kth;                       // connect previous group to new head
            tail.next = groupNext;                      // connect new tail to next group
            groupPrev = tail;                           // advance groupPrev to end of this group
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
        ReverseNodesInKGroup solution = new ReverseNodesInKGroup();

        print(solution.reverseKGroup(build(1, 2, 3, 4, 5), 2)); // Expected: 2 → 1 → 4 → 3 → 5
        print(solution.reverseKGroup(build(1, 2, 3, 4, 5), 3)); // Expected: 3 → 2 → 1 → 4 → 5
        print(solution.reverseKGroup(build(1, 2, 3, 4, 5), 1)); // Expected: 1 → 2 → 3 → 4 → 5
        print(solution.reverseKGroup(build(1, 2), 2));           // Expected: 2 → 1
    }
}
