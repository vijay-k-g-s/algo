package interval;

// Problem (LC 57): Given a list of non-overlapping intervals sorted by start time,
//          insert a new interval and merge if necessary. Return the result sorted.
// Example: intervals = [[1,3],[6,9]], newInterval = [2,5] → [[1,5],[6,9]]
//          intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//          → [[1,2],[3,10],[12,16]]
// Approach: Three phases.
//   1. Add all intervals ending before newInterval starts (no overlap).
//   2. Merge all overlapping intervals with newInterval.
//   3. Add all intervals starting after merged interval ends.
// Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;

        // Phase 1: add intervals before new interval
        while (i < n && intervals[i][1] < newInterval[0]) result.add(intervals[i++]);

        // Phase 2: merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Phase 3: add remaining intervals
        while (i < n) result.add(intervals[i++]);

        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        InsertInterval sol = new InsertInterval();
        System.out.println(Arrays.deepToString(
            sol.insert(new int[][]{{1,3},{6,9}}, new int[]{2,5}))); // [[1,5],[6,9]]
        System.out.println(Arrays.deepToString(
            sol.insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8}))); // [[1,2],[3,10],[12,16]]
    }
}
