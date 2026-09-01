package linked_list;

import java.util.Comparator;
import java.util.PriorityQueue;

    // LeetCode 23. Merge K Sorted Lists
    //
    // Approach: Min-Heap (PriorityQueue)
    //   - Seed heap with head node of each list.
    //   - Poll the globally smallest node, attach to result.
    //   - If polled node has a next, push it into the heap.
    //   - Repeat until heap is empty.
    //
    // Why Min-Heap?
    //   Brute force merging lists one by one is O(kN).
    //   Heap always gives the global minimum in O(log k),
    //   so total cost is O(N log k) where N = total nodes.
    //
    // Complexity:
    //   Time:  O(N log k) — N nodes each pushed/popped once, heap size stays <= k
    //   Space: O(k)       — heap holds at most one node per list

    public class MergeKSortedLists {

        public ListNode mergeKLists(ListNode[] lists) {
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;

            PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

            // Seed heap with head of each list
            for (ListNode head : lists) {
                if (head != null) minHeap.offer(head);
            }

            while (!minHeap.isEmpty()) {
                ListNode node = minHeap.poll();         // globally smallest node
                tail.next = node;
                tail = tail.next;

                if (node.next != null) {
                    minHeap.offer(node.next);           // push next node from same list
                }
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
        System.out.println(sb.length() == 0 ? "(empty)" : sb);
    }

    public static void main(String[] args) {
        MergeKSortedLists solution = new MergeKSortedLists();

        // [1→4→5], [1→3→4], [2→6]
        ListNode[] lists1 = {build(1, 4, 5), build(1, 3, 4), build(2, 6)};
        print(solution.mergeKLists(lists1)); // Expected: 1 → 1 → 2 → 3 → 4 → 4 → 5 → 6

        // empty input
        print(solution.mergeKLists(new ListNode[]{})); // Expected: (empty)

        // single empty list
        print(solution.mergeKLists(new ListNode[]{null})); // Expected: (empty)
    }
}
