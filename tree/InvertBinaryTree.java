package tree;

// Problem (LC 226): Given the root of a binary tree, invert the tree (mirror it),
//          and return its root.
// Example:     4               4
//            /   \    →      /   \
//           2     7         7     2
//          / \   / \       / \   / \
//         1   3 6   9     9   6 3   1
// Approach: DFS (recursive post-order).
//   Swap left and right children at every node.
//   Recursively invert left and right subtrees first, then swap.
// Time: O(n), Space: O(h) recursion stack
//
// ─────────────────────────────────────────────────────────────────────────────

public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left  = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left  = right;
        root.right = left;
        return root;
    }

    public static void main(String[] args) {
        InvertBinaryTree sol = new InvertBinaryTree();
        TreeNode root = new TreeNode(4,
            new TreeNode(2, new TreeNode(1), new TreeNode(3)),
            new TreeNode(7, new TreeNode(6), new TreeNode(9)));
        TreeNode inv = sol.invertTree(root);
        // Expected root: 4, left=7, right=2
        System.out.println("Root: " + inv.val);           // 4
        System.out.println("Left: " + inv.left.val);      // 7
        System.out.println("Right: " + inv.right.val);    // 2
    }
}
