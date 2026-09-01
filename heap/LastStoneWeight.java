package heap;

// Problem (LC 1046): You have a collection of stones. On each turn, take the two
//          heaviest stones and smash them. If equal, both are destroyed. If not,
//          the smaller one is destroyed and the larger is reduced by the smaller weight.
//          Return the weight of the last remaining stone, or 0 if none remain.
// Example: stones = [2, 7, 4, 1, 8, 1]
//          → smash 8,7 → 1. stones = [2,4,1,1,1]
//          → smash 4,2 → 2. stones = [2,1,1,1]
//          → smash 2,1 → 1. stones = [1,1,1]
//          → smash 1,1 → 0. stones = [1]
//          → 1
// Approach: Max-Heap.
//   Repeatedly poll two heaviest; if different, push the difference back.
// Time: O(n log n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int s : stones) maxHeap.offer(s);

        while (maxHeap.size() > 1) {
            int y = maxHeap.poll();
            int x = maxHeap.poll();
            if (y != x) maxHeap.offer(y - x);
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    public static void main(String[] args) {
        LastStoneWeight sol = new LastStoneWeight();
        System.out.println(sol.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1})); // 1
        System.out.println(sol.lastStoneWeight(new int[]{1}));                 // 1
        System.out.println(sol.lastStoneWeight(new int[]{2, 2}));              // 0
    }
}
