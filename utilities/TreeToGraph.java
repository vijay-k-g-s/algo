// Utility (commented out): Convert a binary tree into an undirected Graph.
//   Each tree node becomes a graph node. Parent-child edges become bidirectional.
//   Useful for problems that need to traverse a tree as a general graph
//   (e.g., find distance between two nodes, all paths from any node).
// Approach: DFS from root. For each node, add bidirectional edge to parent.
//   Use a Map<TreeNode, List<TreeNode>> as the adjacency list.
//
//package utilities;
//
//public class TreeToGraph {
//
//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);
//
//        TreeToGraph obj = new TreeToGraph();
//        obj.treeToGraph(root);
//    }
//
//    public void treeToGraph(TreeNode root){
//        Map<TreeNode,List<TreeNode>> graph = new HashMap<>();
//        buildGraph(root,null,graph);
//        System.out.println(graph);
//    }
//
//    private void buildGraph(TreeNode node, TreeNode parent, Map<TreeNode,List<TreeNode>> graph){
//        if(node == null) return;
//
//        if(!graph.containsKey(node)){
//            graph.put(node,new ArrayList<>());
//            if(parent != null){
//                graph.get(node).add(parent);
//                graph.get(parent).add(node);
//            }
//        }
//
//        buildGraph(node.left,parent,graph);
//        buildGraph(node.right,parent,graph);
//    }
//}
//
//private void buildGraph(TreeNode node, TreeNode parent, Map<TreeNode,List<TreeNode>> graph){
//    if(node == null) return;
//
//    if(!graph.containsKey(node)){
//        graph.put(node,new ArrayList<>());
//        if(parent != null){
//            graph.get(node).add(parent);
//            graph.get(parent).add(node);
//        }
//    }
//
//    buildGraph(node.left,parent,graph);
//    buildGraph(node.right,parent,graph);
//}
