package tree;

// Problem (LC 102): Given the root of a binary tree, return the level order
//          traversal of its nodes' values (i.e., left to right, level by level).
// Example:     3        → [[3],[9,20],[15,7]]
//            /   \
//           9    20
//               /  \
//              15   7
//          [] → []
// Approach: BFS with a queue.
//   At each level, snapshot queue size to know how many nodes belong to this level.
//   Process exactly that many nodes, collect values, enqueue their children.
// Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left  != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal sol = new BinaryTreeLevelOrderTraversal();
        TreeNode root = new TreeNode(3,
            new TreeNode(9),
            new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(sol.levelOrder(root)); // [[3],[9,20],[15,7]]
        System.out.println(sol.levelOrder(null)); // []
    }
}
