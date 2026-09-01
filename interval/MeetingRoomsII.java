package interval;

import java.util.*;

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

public class MeetingRoomsII {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        // Sort the intervals based on their start times
        Arrays.sort(intervals, (a, b) -> a.start - b.start);

        // Min heap to store the end times of intervals
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add the first interval's end time
        minHeap.offer(intervals[0].end);

        for (int i = 1; i < intervals.length; i++) {
            Interval currentInterval = intervals[i];
            Integer earliestEndTime = minHeap.poll();

            // If the current interval's start time is later than the earliest end time,
            // we can reuse the room, so update the end time
            if (currentInterval.start >= earliestEndTime) {
                minHeap.offer(currentInterval.end);
            } else {
                // Otherwise, we need to allocate a new room
                minHeap.offer(earliestEndTime);
                minHeap.offer(currentInterval.end);
            }
        }

        // The size of the minHeap represents the number of rooms needed
        return minHeap.size();
    }

    public static void main(String[] args) {
        MeetingRoomsII solution = new MeetingRoomsII();

        // Example usage
        Interval[] intervals = {new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)};
        System.out.println("Minimum number of meeting rooms required: " + solution.minMeetingRooms(intervals));
    }
}

