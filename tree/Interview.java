package tree;

// Problem: Binary Tree In-Order Traversal (Left → Root → Right).
//          For a Binary Search Tree, in-order traversal visits nodes in
//          ascending sorted order.
// Example: Tree:       1
//                    /   \
//                   2     3
//                  / \   / \
//                 4   5 6   7
//          In-order output: 4 2 5 1 6 3 7
// Approach: Recursive DFS — recurse left subtree, print root, recurse right.
//           Base case: node == null → return.
// Time: O(n), Space: O(h) where h = height of tree (recursion stack)
//
// ─────────────────────────────────────────────────────────────────────────────

public class Interview {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        Interview obj = new Interview();
        obj.printTree(root); // Call the printTree method
    }

    public void printTree(TreeNode node) {
        if (node == null) {
            return;
        }
        // In-order traversal: left, root, right
        printTree(node.left);
        System.out.print(node.val + " ");
        printTree(node.right);
    }
    
}
