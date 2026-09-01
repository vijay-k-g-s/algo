package tree;

// Problem (LC 104): Given the root of a binary tree, return its maximum depth —
//          the number of nodes along the longest path from root to a leaf node.
// Example:     3        → depth 3  (path 3→9 or 3→20→15 or 3→20→7)
//            /   \
//           9    20
//               /  \
//              15   7
//          [] (null root) → 0
// Approach: DFS (recursive).
//   maxDepth(root) = 1 + max(maxDepth(left), maxDepth(right))
//   Base case: null → 0.
// Time: O(n), Space: O(h)
//
// ─────────────────────────────────────────────────────────────────────────────

public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static void main(String[] args) {
        MaximumDepthOfBinaryTree sol = new MaximumDepthOfBinaryTree();
        TreeNode root = new TreeNode(3,
            new TreeNode(9),
            new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(sol.maxDepth(root)); // 3
        System.out.println(sol.maxDepth(null)); // 0
    }
}
