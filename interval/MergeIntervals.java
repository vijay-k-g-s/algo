package interval;

// Problem (LC 56): Given an array of intervals, merge all overlapping intervals
//          and return an array of the non-overlapping intervals.
// Example: intervals = [[1,3],[2,6],[8,10],[15,18]] → [[1,6],[8,10],[15,18]]
//          intervals = [[1,4],[4,5]] → [[1,5]]
// Approach: Sort by start time. Iterate and merge if current start <= last end.
//   If no overlap: add previous interval, start new one.
//   If overlap: extend last interval's end to max of both ends.
// Time: O(n log n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] last = result.get(result.size() - 1);
            if (intervals[i][0] <= last[1]) {
                last[1] = Math.max(last[1], intervals[i][1]);
            } else {
                result.add(intervals[i]);
            }
        }
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        MergeIntervals sol = new MergeIntervals();
        System.out.println(Arrays.deepToString(
            sol.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}}))); // [[1,6],[8,10],[15,18]]
        System.out.println(Arrays.deepToString(
            sol.merge(new int[][]{{1,4},{4,5}}))); // [[1,5]]
    }
}
