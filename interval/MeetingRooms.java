package interval;

// Problem (LC 252): Given an array of meeting time intervals [start, end],
//          determine if a person could attend all meetings (no overlaps).
// Example: intervals = [[0,30],[5,10],[15,20]] → false  ([0,30] overlaps with [5,10])
//          intervals = [[7,10],[2,4]] → true  (4 <= 7, no overlap)
// Approach: Sort by start time. Check if any meeting starts before the previous one ends.
// Time: O(n log n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;

public class MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MeetingRooms sol = new MeetingRooms();
        System.out.println(sol.canAttendMeetings(new int[][]{{0,30},{5,10},{15,20}})); // false
        System.out.println(sol.canAttendMeetings(new int[][]{{7,10},{2,4}}));          // true
        System.out.println(sol.canAttendMeetings(new int[][]{}));                      // true
    }
}
