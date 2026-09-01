package tree;

// Problem (LC 572): Given roots of two binary trees root and subRoot, return true
//          if there is a subtree of root with the same structure and node values as subRoot.
// Example: root=[3,4,5,1,2], subRoot=[4,1,2] → true
//          root=[3,4,5,1,2,null,null,null,null,0], subRoot=[4,1,2] → false
// Approach: DFS — for each node in root, check if the subtree rooted there is
//   identical to subRoot using isSameTree.
//   If root is null → false (subRoot not found).
//   If isSameTree(root, subRoot) → true.
//   Else check left and right subtrees.
// Time: O(m*n) where m,n = sizes of trees. Space: O(h)
//
// ─────────────────────────────────────────────────────────────────────────────

public class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        SubtreeOfAnotherTree sol = new SubtreeOfAnotherTree();
        TreeNode root = new TreeNode(3,
            new TreeNode(4, new TreeNode(1), new TreeNode(2)),
            new TreeNode(5));
        TreeNode sub = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        System.out.println(sol.isSubtree(root, sub)); // true

        TreeNode sub2 = new TreeNode(4, new TreeNode(1), null);
        System.out.println(sol.isSubtree(root, sub2)); // false
    }
}
