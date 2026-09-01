package tree;

// Utility: Binary Tree Node used across all tree problems.
//   val   — integer value stored at this node.
//   left  — left child (null if none).
//   right — right child (null if none).
//
// ─────────────────────────────────────────────────────────────────────────────

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val; this.left = left; this.right = right;
    }
}
