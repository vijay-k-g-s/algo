package graph;

import java.util.*;

// LeetCode 210. Course Schedule II
//
// Given numCourses and prerequisites[i] = [a, b] (take b before a),
// return a valid ordering to finish all courses, or [] if impossible (cycle).
//
// Approach: Kahn's BFS Topological Sort (same as CourseSchedule1)
//   CourseSchedule1 → returns boolean (cycle or not)  → counts visited nodes
//   CourseSchedule2 → returns int[]  (actual order)   → collects visited nodes
//
//   1. Build directed graph: b -> a for each prerequisite [a, b]
//   2. Compute in-degree for every node
//   3. Enqueue all nodes with in-degree 0 (no prerequisites)
//   4. BFS: poll node → add to order → decrement neighbors' in-degree
//            → enqueue neighbor if in-degree hits 0
//   5. If order contains all courses → valid order; else cycle → return []
//
// Complexity:
//   Time:  O(V + E)
//   Space: O(V + E)

public class CourseSchedule2 {

    private DirectedGraph buildGraph(int numCourses, int[][] prerequisites) {
        DirectedGraph g = new DirectedGraph(numCourses);
        for (int[] pre : prerequisites) {
            g.addEdge(pre[1], pre[0]);          // pre[1] must come before pre[0]
        }
        return g;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        DirectedGraph g = buildGraph(numCourses, prerequisites);

        // In-degree for every course (including isolated ones with no edges)
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int i = 0; i < numCourses; i++) inDegree.put(i, 0);
        for (int node : g.adjList.keySet()) {
            for (int neighbor : g.getConnectedNodes(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        // Enqueue courses with no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) queue.add(node);
        }

        int[] order = new int[numCourses];
        int idx = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            order[idx++] = node;                // collect course in topological order

            for (int neighbor : g.getConnectedNodes(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) queue.add(neighbor);
            }
        }

        return idx == numCourses ? order : new int[]{};  // cycle → empty
    }

    public static void main(String[] args) {
        CourseSchedule2 cs = new CourseSchedule2();

        // [0, 1] — take course 1 then 0
        System.out.println(Arrays.toString(cs.findOrder(2, new int[][]{{1, 0}})));

        // [0, 2, 1, 3] or any valid topo order
        System.out.println(Arrays.toString(cs.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));

        // cycle → []
        System.out.println(Arrays.toString(cs.findOrder(2, new int[][]{{1, 0},{0, 1}})));

        // single course, no prerequisites → [0]
        System.out.println(Arrays.toString(cs.findOrder(1, new int[][]{})));
    }
}
