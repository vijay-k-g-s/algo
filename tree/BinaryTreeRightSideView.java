package tree;

// Problem (LC 199): Given the root of a binary tree, imagine standing on the right
//          side. Return the values of the nodes visible from the right side
//          (rightmost node at each level).
// Example:     1        → [1, 3, 4]
//            /   \
//           2     3
//            \     \
//             5     4
// Approach: BFS level order traversal. At each level, the last node polled is
//   visible from the right side. Add it to the result.
// Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) result.add(node.val); // rightmost at this level
                if (node.left  != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BinaryTreeRightSideView sol = new BinaryTreeRightSideView();
        TreeNode root = new TreeNode(1,
            new TreeNode(2, null, new TreeNode(5)),
            new TreeNode(3, null, new TreeNode(4)));
        System.out.println(sol.rightSideView(root)); // [1, 3, 4]
        System.out.println(sol.rightSideView(null)); // []
    }
}
