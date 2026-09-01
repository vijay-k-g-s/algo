package tree;

// Problem (LC 98): Given the root of a binary tree, determine if it is a valid
//          Binary Search Tree (BST). A valid BST requires:
//          - Left subtree contains only nodes with values < node.val
//          - Right subtree contains only nodes with values > node.val
//          - Both left and right subtrees are also valid BSTs.
// Example: [2,1,3]       → true
//          [5,1,4,null,null,3,6] → false (4 is in right subtree but < 5)
// Approach: DFS with valid range [min, max] passed down.
//   Initially range is (-∞, +∞).
//   Going left: update max = node.val.
//   Going right: update min = node.val.
//   If node.val <= min or node.val >= max → invalid.
// Time: O(n), Space: O(h)
//
// ─────────────────────────────────────────────────────────────────────────────

public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public static void main(String[] args) {
        ValidateBinarySearchTree sol = new ValidateBinarySearchTree();
        System.out.println(sol.isValidBST(new TreeNode(2, new TreeNode(1), new TreeNode(3)))); // true
        TreeNode invalid = new TreeNode(5,
            new TreeNode(1),
            new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        System.out.println(sol.isValidBST(invalid)); // false
    }
}
