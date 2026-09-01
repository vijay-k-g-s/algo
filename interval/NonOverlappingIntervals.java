package interval;

// Problem (LC 435): Given an array of intervals, return the minimum number of
//          intervals to remove to make the rest non-overlapping.
// Example: intervals = [[1,2],[2,3],[3,4],[1,3]] → 1  (remove [1,3])
//          intervals = [[1,2],[1,2],[1,2]] → 2
//          intervals = [[1,2],[2,3]] → 0
// Approach: Greedy — sort by end time.
//   Greedily keep intervals with earliest end times (leaves most room for future).
//   Track prevEnd. If current start < prevEnd → overlap → remove current (count++).
//   Else keep it (update prevEnd).
// Time: O(n log n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;

public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // sort by end time
        int removed = 0, prevEnd = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            if (interval[0] >= prevEnd) {
                prevEnd = interval[1]; // keep this interval
            } else {
                removed++; // remove current (overlaps with previous)
            }
        }
        return removed;
    }

    public static void main(String[] args) {
        NonOverlappingIntervals sol = new NonOverlappingIntervals();
        System.out.println(sol.eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}})); // 1
        System.out.println(sol.eraseOverlapIntervals(new int[][]{{1,2},{1,2},{1,2}}));       // 2
        System.out.println(sol.eraseOverlapIntervals(new int[][]{{1,2},{2,3}}));             // 0
    }
}
