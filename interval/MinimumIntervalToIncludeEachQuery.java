package interval;

// Problem (LC 1851): Given intervals and queries, for each query find the size of
//          the smallest interval [l, r] that contains the query point (l <= query <= r).
//          Interval size = r - l + 1. Return -1 if no such interval exists.
// Example: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
//          Output: [3,3,1,4]
//          intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
//          Output: [2,-1,4,6]
// Approach: Sort intervals by left endpoint, sort queries (with original index).
//   Use a min-heap sorted by interval size. For each query (in sorted order):
//     Add all intervals whose left <= query into the heap.
//     Remove intervals from heap top whose right < query (expired).
//     Heap top = smallest valid interval for this query.
// Time: O((n + q) log n), Space: O(n + q)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumIntervalToIncludeEachQuery {

    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int q = queries.length;
        Integer[] idx = new Integer[q];
        for (int i = 0; i < q; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> queries[a] - queries[b]);

        // Min-heap: [size, right]
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] result = new int[q];
        int i = 0;

        for (int qi : idx) {
            int query = queries[qi];
            while (i < intervals.length && intervals[i][0] <= query) {
                int size = intervals[i][1] - intervals[i][0] + 1;
                heap.offer(new int[]{size, intervals[i][1]});
                i++;
            }
            while (!heap.isEmpty() && heap.peek()[1] < query) heap.poll();
            result[qi] = heap.isEmpty() ? -1 : heap.peek()[0];
        }
        return result;
    }

    public static void main(String[] args) {
        MinimumIntervalToIncludeEachQuery sol = new MinimumIntervalToIncludeEachQuery();
        System.out.println(Arrays.toString(sol.minInterval(
            new int[][]{{1,4},{2,4},{3,6},{4,4}}, new int[]{2,3,4,5}))); // [3,3,1,4]
        System.out.println(Arrays.toString(sol.minInterval(
            new int[][]{{2,3},{2,5},{1,8},{20,25}}, new int[]{2,19,5,22}))); // [2,-1,4,6]
    }
}
