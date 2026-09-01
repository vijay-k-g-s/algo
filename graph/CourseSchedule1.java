package graph;

// Problem (LC 207): There are numCourses courses (0 to numCourses-1).
//          prerequisites[i] = [a, b] means you must take b before a.
//          Return true if it is possible to finish all courses (no cycle exists).
// Example: numCourses = 2, prerequisites = [[1, 0]]
//          Output: true  (take 0 then 1)
//          numCourses = 2, prerequisites = [[1, 0], [0, 1]]
//          Output: false  (cycle: 0 requires 1 and 1 requires 0)
// Approach: Kahn's Algorithm (BFS Topological Sort).
//   Build a directed graph where b → a means b is prerequisite of a.
//   Compute in-degree for each node. Enqueue all nodes with in-degree 0.
//   BFS: poll a node, decrement neighbors' in-degrees; if in-degree hits 0, enqueue.
//   If visited count == numCourses, no cycle → all courses can be finished.
// Time: O(V + E), Space: O(V + E)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class CourseSchedule1 {

    DirectedGraph buildGraph(int numCourses, int[][] prerequisites) {

        DirectedGraph g = new DirectedGraph(numCourses);

//        for (int i = 0; i < numCourses; i++) {
//            g.adjList.putIfAbsent(i, new ArrayList<>());
//        }

        // prerequisites[i] = [a, b] means b -> a (take b before a)
        for (int[] pre : prerequisites) {
            g.addEdge(pre[1], pre[0]);
        }

        return g;
    }

    boolean canFinish(int numCourses, int[][] prerequisites) {

        DirectedGraph g = buildGraph(numCourses, prerequisites);

        // Calculate in-degree of each node
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int node : g.adjList.keySet()) {
            inDegree.put(node, 0);
        }
        for (int node : g.adjList.keySet()) {
            for (int neighbor : g.getConnectedNodes(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        // Enqueue all nodes with in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.add(node);
            }
        }

        int visited = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;

            for (int neighbor : g.getConnectedNodes(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // If all courses were visited, no cycle exists
        return visited == numCourses;
    }

    public static void main(String[] args) {

        CourseSchedule1 cs = new CourseSchedule1();

        // Example 1: 2 courses, take course 0 before 1 -> no cycle -> true
        int[][] pre1 = {{1, 0}};
        System.out.println("Can finish (ex1): " + cs.canFinish(2, pre1));

        // Example 2: cycle 0 -> 1 -> 0 -> false
        int[][] pre2 = {{1, 0}, {0, 1}};
        System.out.println("Can finish (ex2): " + cs.canFinish(2, pre2));

        // Example 3: 4 courses, no cycle -> true
        int[][] pre3 = {{1, 0}, {2, 1}, {3, 2}};
        System.out.println("Can finish (ex3): " + cs.canFinish(4, pre3));
    }
}
