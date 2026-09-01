package tree;

// Problem (LC 230): Given the root of a BST and an integer k, return the kth
//          smallest value (1-indexed) among all node values in the tree.
// Example: root=[3,1,4,null,2], k=1 → 1
//          root=[5,3,6,2,4,null,null,1], k=3 → 3
// Approach: In-order traversal of a BST visits nodes in ascending order.
//   Perform iterative in-order traversal, decrement k at each visited node.
//   When k == 0, we've found the kth smallest.
// Time: O(H + k) where H = height. Space: O(H)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Stack;

public class KthSmallestInBST {

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) { stack.push(curr); curr = curr.left; }
            curr = stack.pop();
            if (--k == 0) return curr.val;
            curr = curr.right;
        }
        return -1;
    }

    public static void main(String[] args) {
        KthSmallestInBST sol = new KthSmallestInBST();
        TreeNode root1 = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));
        System.out.println(sol.kthSmallest(root1, 1)); // 1

        TreeNode root2 = new TreeNode(5,
            new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4)),
            new TreeNode(6));
        System.out.println(sol.kthSmallest(root2, 3)); // 3
    }
}
