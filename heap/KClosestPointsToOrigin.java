package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    record Point(int x, int y) {
        int distSq() { return x * x + y * y; } // avoid sqrt — comparison safe
        @Override public String toString() { return "[" + x + "," + y + "]"; }
    }

    // Approach 1: Max-Heap of size k — O(n log k) time, O(k) space
    // Keep k closest points; evict the farthest when size exceeds k
    public int[][] usingMaxHeap(int[][] points, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(
            (a, b) -> b.distSq() - a.distSq() // farthest on top
        );

        for (int[] p : points) {
            maxHeap.offer(new Point(p[0], p[1]));
            if (maxHeap.size() > k) {
                maxHeap.poll(); // remove farthest
            }
        }

        int[][] result = new int[k][2];
        for (int i = k - 1; i >= 0; i--) {
            Point p = maxHeap.poll();
            result[i] = new int[]{p.x, p.y};
        }
        return result;
    }

    // Approach 2: QuickSelect — O(n) average time, O(1) space
    // Partition points so first k have smallest distances
    public int[][] usingQuickSelect(int[][] points, int k) {
        quickSelect(points, 0, points.length - 1, k);
        return Arrays.copyOfRange(points, 0, k);
    }

    private void quickSelect(int[][] points, int lo, int hi, int k) {
        if (lo >= hi) return;
        int p = partition(points, lo, hi);
        if (p + 1 == k) return;
        if (p + 1 < k)  quickSelect(points, p + 1, hi, k);
        else            quickSelect(points, lo, p - 1, k);
    }

    private int partition(int[][] points, int lo, int hi) {
        int pivotDist = distSq(points[hi]);
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (distSq(points[j]) <= pivotDist) {
                swap(points, i++, j);
            }
        }
        swap(points, i, hi);
        return i;
    }

    private int distSq(int[] p) { return p[0] * p[0] + p[1] * p[1]; }

    private void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    private String format(int[][] points) {
        StringBuilder sb = new StringBuilder("[");
        for (int[] p : points) sb.append(Arrays.toString(p)).append(", ");
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin sol = new KClosestPointsToOrigin();

        int[][] points1 = {{1, 3}, {-2, 2}, {5, 8}, {0, 1}};
        int k1 = 2;
        System.out.println("Points: " + sol.format(points1) + ", k=" + k1);
        System.out.println("Max-Heap:    " + sol.format(sol.usingMaxHeap(points1.clone(), k1)));
        System.out.println("QuickSelect: " + sol.format(sol.usingQuickSelect(points1.clone(), k1)));

        System.out.println();
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        int k2 = 2;
        System.out.println("Points: " + sol.format(points2) + ", k=" + k2);
        System.out.println("Max-Heap:    " + sol.format(sol.usingMaxHeap(points2.clone(), k2)));
        System.out.println("QuickSelect: " + sol.format(sol.usingQuickSelect(points2.clone(), k2)));
    }
}
