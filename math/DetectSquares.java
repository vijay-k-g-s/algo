package math;

// Problem (LC 2013): Design a data structure for counting points on a 2D plane
//          that supports:
//   add(point) — add a point (may be duplicate).
//   count(point) — count axis-aligned squares with point as one corner.
//          All 4 vertices must exist in the structure.
// Example: add([3,10]), add([11,2]), add([3,2])
//          count([11,10]) → 1  (square with corners [3,10],[11,10],[3,2],[11,2])
//          add([14,8])
//          count([11,2]) → 0
//          add([11,2])
//          count([11,10]) → 2
// Approach: Store all points in a HashMap (x → {y → count}).
//   For count(px, py): find all diagonal candidates by matching x != px.
//   For each such point (x2, py): diagonally opposite corner at (x2, py).
//   The square has side = |x2 - px|. Check if (px, y3) and (x2, y3) both exist
//   where y3 = py ± side. Multiply counts.
// Time: O(n) add, O(n) count. Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.HashMap;
import java.util.Map;

public class DetectSquares {

    private final Map<Integer, Map<Integer, Integer>> xToYCount = new HashMap<>();
    private final Map<String, Integer> pointCount = new HashMap<>();

    public void add(int[] point) {
        int x = point[0], y = point[1];
        xToYCount.computeIfAbsent(x, k -> new HashMap<>()).merge(y, 1, Integer::sum);
        pointCount.merge(x + "," + y, 1, Integer::sum);
    }

    public int count(int[] point) {
        int px = point[0], py = point[1];
        int total = 0;

        Map<Integer, Integer> xMap = xToYCount.getOrDefault(px, new HashMap<>());
        for (Map.Entry<Integer, Integer> e : xToYCount.entrySet()) {
            int x2 = e.getKey();
            if (x2 == px) continue;
            Map<Integer, Integer> x2Map = e.getValue();

            // Diagonal point (x2, py) must exist
            int diagCount = x2Map.getOrDefault(py, 0);
            if (diagCount == 0) continue;

            int side = Math.abs(x2 - px);
            // Two possible squares: y3 = py + side or py - side
            for (int y3 : new int[]{py + side, py - side}) {
                int c1 = xMap.getOrDefault(y3, 0);      // (px, y3)
                int c2 = x2Map.getOrDefault(y3, 0);     // (x2, y3)
                total += diagCount * c1 * c2;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        DetectSquares ds = new DetectSquares();
        ds.add(new int[]{3, 10});
        ds.add(new int[]{11, 2});
        ds.add(new int[]{3, 2});
        System.out.println(ds.count(new int[]{11, 10})); // 1
        ds.add(new int[]{14, 8});
        System.out.println(ds.count(new int[]{11, 2}));  // 0
        ds.add(new int[]{11, 2});
        System.out.println(ds.count(new int[]{11, 10})); // 2
    }
}
