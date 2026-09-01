package linked_list;

// Utility: Singly Linked List Node used across all linked_list problems.
//   val  — the integer value stored at this node.
//   next — reference to the next node; null if this is the tail.
// Structure: head → [1] → [2] → [3] → null
//
// ─────────────────────────────────────────────────────────────────────────────

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}
