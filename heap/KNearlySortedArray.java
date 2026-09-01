package heap;

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
