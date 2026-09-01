package tree;

// Problem (LC 105): Given preorder and inorder traversal arrays, construct and
//          return the binary tree.
// Example: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//          Output: [3,9,20,null,null,15,7]
// Approach: Recursive.
//   preorder[0] is always the root.
//   Find root in inorder → splits into left and right subtrees.
//   Left subtree size = index of root in inorder.
//   Recurse on left: preorder[1..leftSize], inorder[0..rootIdx-1]
//   Recurse on right: preorder[leftSize+1..], inorder[rootIdx+1..]
//   Use a HashMap for O(1) inorder index lookup.
// Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderInorder {

    private Map<Integer, Integer> inorderIndex;
    private int preIdx = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inorderIndex.put(inorder[i], i);
        preIdx = 0;
        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int left, int right) {
        if (left > right) return null;
        int rootVal = preorder[preIdx++];
        TreeNode root = new TreeNode(rootVal);
        int mid = inorderIndex.get(rootVal);
        root.left  = build(preorder, left, mid - 1);
        root.right = build(preorder, mid + 1, right);
        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderInorder sol = new ConstructBinaryTreeFromPreorderInorder();
        TreeNode root = sol.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        System.out.println(root.val);       // 3
        System.out.println(root.left.val);  // 9
        System.out.println(root.right.val); // 20
    }
}
