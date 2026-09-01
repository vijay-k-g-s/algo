package tree;

// Problem (LC 543): Given the root of a binary tree, return the length of the
//          diameter — the longest path between any two nodes. The path may or
//          may not pass through the root. Length = number of edges.
// Example:     1        → 3  (path 4→2→1→3 or 5→2→1→3, length = 3 edges)
//            /   \
//           2     3
//          / \
//         4   5
//          [1, 2] → 1
// Approach: DFS — for each node compute height of left and right subtrees.
//   Diameter through this node = leftHeight + rightHeight.
//   Track global max diameter via an instance variable.
//   Return height = 1 + max(leftHeight, rightHeight) up to parent.
// Time: O(n), Space: O(h)
//
// ─────────────────────────────────────────────────────────────────────────────

public class DiameterOfBinaryTree {

    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        height(root);
        return maxDiameter;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        int left  = height(node.left);
        int right = height(node.right);
        maxDiameter = Math.max(maxDiameter, left + right);
        return 1 + Math.max(left, right);
    }

    public static void main(String[] args) {
        DiameterOfBinaryTree sol = new DiameterOfBinaryTree();
        TreeNode root = new TreeNode(1,
            new TreeNode(2, new TreeNode(4), new TreeNode(5)),
            new TreeNode(3));
        System.out.println(sol.diameterOfBinaryTree(root)); // 3
        System.out.println(sol.diameterOfBinaryTree(new TreeNode(1, new TreeNode(2), null))); // 1
    }
}
