package heap;

// Problem (LC 621): Given a list of tasks (characters A-Z) and a cooldown n,
//          each same-type task must be separated by at least n intervals.
//          Return the minimum number of intervals (including idle) to finish all tasks.
// Example: tasks = ['A','A','A','B','B','B'], n = 2
//          Output: 8  → A B idle A B idle A B
//          tasks = ['A','A','A','B','B','B'], n = 0 → 6 (no cooldown)
//          tasks = ['A','A','A','A','A','A','B','C','D','E','F','G'], n = 2 → 16
// Approach: Greedy with Max-Heap + Queue.
//   Frequencies in max-heap. At each cycle, schedule up to (n+1) tasks,
//   picking most frequent available. Track time to re-add cooling tasks.
// Time: O(N * n) where N = tasks length. Space: O(1) — at most 26 entries.
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;

public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char t : tasks) freq.merge(t, 1, Integer::sum);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(freq.values());

        Queue<int[]> cooldown = new LinkedList<>(); // [remaining_count, available_at_time]
        int time = 0;

        while (!maxHeap.isEmpty() || !cooldown.isEmpty()) {
            time++;
            if (!maxHeap.isEmpty()) {
                int count = maxHeap.poll() - 1;
                if (count > 0) cooldown.offer(new int[]{count, time + n});
            }
            if (!cooldown.isEmpty() && cooldown.peek()[1] == time) {
                maxHeap.offer(cooldown.poll()[0]);
            }
        }
        return time;
    }

    public static void main(String[] args) {
        TaskScheduler sol = new TaskScheduler();
        System.out.println(sol.leastInterval(new char[]{'A','A','A','B','B','B'}, 2)); // 8
        System.out.println(sol.leastInterval(new char[]{'A','A','A','B','B','B'}, 0)); // 6
        System.out.println(sol.leastInterval(new char[]{'A','A','A','B','B','C'}, 2)); // 7
    }
}
