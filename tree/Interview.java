package tree;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

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
        System.out.print(node.value + " ");
        printTree(node.right);
    }
    
}
