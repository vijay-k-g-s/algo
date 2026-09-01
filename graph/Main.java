package graph;

// Scratch / Playground — graph package experiments.
//   Contains early Dijkstra prototype (dijalgo) and buildGraphFromMatrix utility.
//   Not a standalone problem solution — used for quick manual testing.
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class Main {

    public static void main(String[] args) {

//        Graph g = new Graph(5);
//
//        g.addEdge(0, 1);
//        g.addEdge(0, 2);
//        g.addEdge(1, 3);
//        g.addEdge(2, 4);
//
//        for (int node : g.adjList.keySet()) {
//            System.out.println(node + " -> " + g.getConnectedNodes(node));
//        }

        int[][] grid1 = {
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 1, 0}
        };

        System.out.println(buildGraphFromMatrix(grid1).adjList);



    }

    static Map<Integer, List<int[]>> buildGraph(int[][] times) {

        Map<Integer, List<int[]>> map = new HashMap<>();

        for (int[] time : times) {
            map.put(time[0], new ArrayList<>());
        }

        for (int[] time : times) {
            map.get(time[0]).add(new int[]{time[1], time[2]});
        }

        return map;
    }

    static int dijalgo(int[][] times, int k, int n) {

        Map<Integer, List<int[]>> graph = buildGraph(times);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        minHeap.add(new int[]{0, k});
        Map<Integer, Integer> dist = new HashMap<>();

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int cost = curr[0];
            int node = curr[1];

            if (dist.containsKey(node)) continue;
            dist.put(node, cost);

            for (int[] next : graph.get(node)) {

                int d = next[1];
                int nextNode = next[0];


                if (!dist.containsKey(nextNode)) {
                    minHeap.add(new int[]{d + cost, nextNode});
                }


            }
        }

        if (dist.size() != n) return -1;

        return Collections.max(dist.values());


    }

    static Graph buildGraphFromMatrix(int[][] matrix) {

        int[][] DIRS = {{0, 1},{1, 0}};
        int rows = matrix.length;
        int cols = matrix[0].length;
        Graph g = new Graph(rows);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 1) {
                    int node = r * cols + c;
                    g.adjList.putIfAbsent(node, new ArrayList<>());
                    for (int[] dir : DIRS) {
                        int nr = dir[0] + r;
                        int nc = dir[1] + c;
                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && matrix[nr][nc]==1) {
                            g.addEdge(node,nr * cols + nc);
                        }
                    }
                }
            }
        }
        return g;
    }
}
