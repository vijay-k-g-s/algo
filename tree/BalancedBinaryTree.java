package tree;

// Problem (LC 110): Given a binary tree, determine if it is height-balanced —
//          for every node, the heights of left and right subtrees differ by at most 1.
// Example:     3        → true
//            /   \
//           9    20
//               /  \
//              15   7
//
//          [1,2,2,3,3,null,null,4,4] → false
// Approach: DFS — return height of subtree, or -1 if unbalanced.
//   At each node: if left or right returns -1, propagate -1 upward.
//   If |leftH - rightH| > 1 → return -1 (unbalanced).
//   Otherwise return 1 + max(leftH, rightH).
// Time: O(n), Space: O(h)
//
// ─────────────────────────────────────────────────────────────────────────────

public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int left  = checkHeight(node.left);
        if (left == -1) return -1;
        int right = checkHeight(node.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return 1 + Math.max(left, right);
    }

    public static void main(String[] args) {
        BalancedBinaryTree sol = new BalancedBinaryTree();
        TreeNode balanced = new TreeNode(3,
            new TreeNode(9),
            new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(sol.isBalanced(balanced)); // true

        TreeNode unbalanced = new TreeNode(1,
            new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null), null);
        System.out.println(sol.isBalanced(unbalanced)); // false
    }
}
