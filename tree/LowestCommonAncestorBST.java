package tree;

// Problem (LC 235): Given a BST and two nodes p and q, find their Lowest Common
//          Ancestor (LCA) — the deepest node that has both p and q as descendants
//          (a node is a descendant of itself).
// Example: root=[6,2,8,0,4,7,9], p=2, q=8 → 6
//          root=[6,2,8,0,4,7,9], p=2, q=4 → 2
// Approach: Use BST property.
//   If both p and q values < root → LCA is in left subtree.
//   If both p and q values > root → LCA is in right subtree.
//   Otherwise (split point) → current root is the LCA.
// Time: O(h), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class LowestCommonAncestorBST {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root; // split point — this is the LCA
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LowestCommonAncestorBST sol = new LowestCommonAncestorBST();
        TreeNode root = new TreeNode(6,
            new TreeNode(2, new TreeNode(0), new TreeNode(4)),
            new TreeNode(8, new TreeNode(7), new TreeNode(9)));
        System.out.println(sol.lowestCommonAncestor(root, new TreeNode(2), new TreeNode(8)).val); // 6
        System.out.println(sol.lowestCommonAncestor(root, new TreeNode(2), new TreeNode(4)).val); // 2
    }
}
