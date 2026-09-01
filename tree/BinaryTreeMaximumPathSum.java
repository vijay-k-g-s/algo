package tree;

// Problem (LC 124): Given the root of a binary tree, return the maximum path sum
//          of any non-empty path. A path is a sequence of nodes where each pair
//          of adjacent nodes has an edge. A node can only appear once.
// Example: root = [1,2,3]          → 6   (path 2→1→3)
//          root = [-10,9,20,null,null,15,7] → 42  (path 15→20→7)
// Approach: DFS — for each node compute the max gain including it.
//   gain(node) = node.val + max(gain(left), gain(right), 0)
//   (take the better child; ignore if negative)
//   Path through node = node.val + max(gain(left),0) + max(gain(right),0)
//   Track global max path sum seen.
//   Return only one direction up to the parent (can't split at parent level).
// Time: O(n), Space: O(h)
//
// ─────────────────────────────────────────────────────────────────────────────

public class BinaryTreeMaximumPathSum {

    private int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        gain(root);
        return maxSum;
    }

    private int gain(TreeNode node) {
        if (node == null) return 0;
        int leftGain  = Math.max(gain(node.left),  0);
        int rightGain = Math.max(gain(node.right), 0);
        maxSum = Math.max(maxSum, node.val + leftGain + rightGain);
        return node.val + Math.max(leftGain, rightGain); // only one branch goes up
    }

    public static void main(String[] args) {
        BinaryTreeMaximumPathSum sol = new BinaryTreeMaximumPathSum();
        System.out.println(sol.maxPathSum(new TreeNode(1, new TreeNode(2), new TreeNode(3)))); // 6
        TreeNode root = new TreeNode(-10,
            new TreeNode(9),
            new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(sol.maxPathSum(root)); // 42
        System.out.println(sol.maxPathSum(new TreeNode(-3))); // -3
    }
}
