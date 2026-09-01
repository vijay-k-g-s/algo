package tree;

// Problem (LC 100): Given the roots of two binary trees, check if they are the same —
//          structurally identical and nodes have the same values.
// Example: [1,2,3] and [1,2,3] → true
//          [1,2]   and [1,null,2] → false  (different structure)
//          [1,2,1] and [1,1,2]   → false  (different values)
// Approach: DFS (recursive).
//   Both null → true.
//   One null, other not → false.
//   Values differ → false.
//   Recurse on left and right subtrees.
// Time: O(n), Space: O(h)
//
// ─────────────────────────────────────────────────────────────────────────────

public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        SameTree sol = new SameTree();
        TreeNode t1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode t2 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(sol.isSameTree(t1, t2)); // true

        TreeNode t3 = new TreeNode(1, new TreeNode(2), null);
        TreeNode t4 = new TreeNode(1, null, new TreeNode(2));
        System.out.println(sol.isSameTree(t3, t4)); // false
    }
}
