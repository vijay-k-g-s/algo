package heap;

// Problem: Sort a k-nearly sorted array. Each element is at most k positions
//          away from its correct sorted position. Return the fully sorted array.
// Example: nums = [6, 5, 3, 2, 8, 10, 9], k = 3
//          Output: [2, 3, 5, 6, 8, 9, 10]
//          nums = [10, 9, 8, 7, 4, 70, 60, 50], k = 4
//          Output: [4, 7, 8, 9, 10, 50, 60, 70]
// Approach: Min-Heap of size (k+1).
//   The globally smallest element must be within the first k+1 elements.
//   Seed heap with indices 0..k. Then for each new element added, poll the
//   min (guaranteed correct next element) and push the new one.
//   Drain remaining heap at the end.
// Time: O(n log k), Space: O(k)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;
import java.util.PriorityQueue;

public class KNearlySortedArray {

    // Min-Heap of size (k+1) — O(n log k) time, O(k) space
    // Each element is at most k positions away from its sorted position.
    // So the smallest element is always within the first k+1 elements.
    public int[] sort(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int[] result = new int[nums.length];
        int idx = 0;

        // Fill heap with first k+1 elements
        for (int i = 0; i <= k && i < nums.length; i++) {
            minHeap.offer(nums[i]);
        }

        // For each remaining element: extract min → result, add new element
        for (int i = k + 1; i < nums.length; i++) {
            result[idx++] = minHeap.poll();
            minHeap.offer(nums[i]);
        }

        // Drain remaining elements
        while (!minHeap.isEmpty()) {
            result[idx++] = minHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        KNearlySortedArray sol = new KNearlySortedArray();

        int[] nums = {6, 5, 3, 2, 8, 10, 9};
        int k = 3;

        System.out.println("Input:  " + Arrays.toString(nums));
        System.out.println("k = " + k);
        System.out.println("Sorted: " + Arrays.toString(sol.sort(nums, k)));
    }
}
