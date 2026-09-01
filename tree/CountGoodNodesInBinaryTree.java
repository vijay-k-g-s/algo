package tree;

// Problem (LC 1448): Given the root of a binary tree, return the number of good nodes.
//          A node X is good if no node on the path from root to X has a value greater than X.
// Example: root = [3,1,4,3,null,1,5] → 4
//          (3 is good, 4 is good, 5 is good, 3 (left child of 1) is good)
//          root = [3,3,null,4,2] → 3
// Approach: DFS, tracking the maximum value seen on the path from root to current node.
//   A node is good if node.val >= maxSoFar.
//   Pass updated max (max of current max and node.val) to children.
// Time: O(n), Space: O(h)
//
// ─────────────────────────────────────────────────────────────────────────────

public class CountGoodNodesInBinaryTree {

    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) return 0;
        int good = node.val >= maxSoFar ? 1 : 0;
        int newMax = Math.max(maxSoFar, node.val);
        return good + dfs(node.left, newMax) + dfs(node.right, newMax);
    }

    public static void main(String[] args) {
        CountGoodNodesInBinaryTree sol = new CountGoodNodesInBinaryTree();
        TreeNode root = new TreeNode(3,
            new TreeNode(1, new TreeNode(3), null),
            new TreeNode(4, new TreeNode(1), new TreeNode(5)));
        System.out.println(sol.goodNodes(root)); // 4
        System.out.println(sol.goodNodes(new TreeNode(3))); // 1
    }
}
