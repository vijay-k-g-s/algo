//package tree;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class TreeNode {
//    int value;
//    TreeNode left;
//    TreeNode right;
//
//    TreeNode(int value) {
//        this.value = value;
//        this.left = null;
//        this.right = null;
//    }
//}
//
//
//public class BranchSums {
//
//    public static List<Integer> branchSums(TreeNode root) {
//        List<Integer> sums = new ArrayList<>();
//        calculateBranchSums(root, 0, sums);
//        return sums;
//    }
//
//    public static void calculateBranchSums(TreeNode node, int runningSum, List<Integer> sums) {
//        if (node == null) {
//            return;
//        }
//
//        int newRunningSum = runningSum + node.value;
//        if (node.left == null && node.right == null) {
//            sums.add(newRunningSum);
//            return;
//        }
//
//        calculateBranchSums(node.left, newRunningSum, sums);
//        calculateBranchSums(node.right, newRunningSum, sums);
//    }
//
//    public static void main(String[] args) {
//        // Example usage:
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//        root.right.right = new TreeNode(6);
//
//        List<Integer> result = branchSums(root);
//        System.out.println(result);  // Output: [7, 8, 10]
//    }
//
//}
