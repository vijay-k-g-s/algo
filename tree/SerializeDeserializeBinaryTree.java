package tree;

// Problem (LC 297): Design an algorithm to serialize a binary tree to a string
//          and deserialize the string back to the original tree structure.
// Example: root = [1,2,3,null,null,4,5]
//          serialize → "1,2,N,N,3,4,N,N,5,N,N"
//          deserialize → reconstructs original tree
// Approach: Pre-order DFS (root, left, right).
//   Serialize: visit each node; append val or "N" for null, separated by ",".
//   Deserialize: split by ","; use a queue. Pop front:
//     If "N" → return null. Else create node, recursively build left then right.
// Time: O(n) both. Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBinaryTree {

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeDFS(root, sb);
        return sb.toString();
    }

    private void serializeDFS(TreeNode node, StringBuilder sb) {
        if (node == null) { sb.append("N,"); return; }
        sb.append(node.val).append(',');
        serializeDFS(node.left, sb);
        serializeDFS(node.right, sb);
    }

    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeDFS(queue);
    }

    private TreeNode deserializeDFS(Queue<String> queue) {
        String val = queue.poll();
        if ("N".equals(val)) return null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left  = deserializeDFS(queue);
        node.right = deserializeDFS(queue);
        return node;
    }

    public static void main(String[] args) {
        SerializeDeserializeBinaryTree sol = new SerializeDeserializeBinaryTree();
        TreeNode root = new TreeNode(1,
            new TreeNode(2),
            new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        String serialized = sol.serialize(root);
        System.out.println("Serialized: " + serialized);
        TreeNode deserialized = sol.deserialize(serialized);
        System.out.println("Root: " + deserialized.val);       // 1
        System.out.println("Right: " + deserialized.right.val); // 3
    }
}
