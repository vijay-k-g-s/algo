package heap;

// Problem: Given an unsorted integer array, find the kth smallest element (1-indexed).
// Example: nums = [7, 10, 4, 3, 20, 15], k = 3
//          Sorted: [3, 4, 7, 10, 15, 20] → 3rd smallest = 7
//          nums = [7, 10, 4, 3, 20, 15], k = 4 → 10
// Approach 1: Max-Heap of size k — keep k smallest seen so far.
//   If size > k, evict the largest. Heap top = kth smallest.
//   Time: O(n log k), Space: O(k)
// Approach 2: QuickSelect — partition so index k-1 holds the kth smallest.
//   Return nums[p] when p == target (= k-1).
//   Time: O(n) average, Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.PriorityQueue;
import java.util.Collections;

public class KthSmallest {

    // Approach 1: Max-Heap of size k — O(n log k) time, O(k) space
    public int usingMaxHeap(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    // Approach 2: QuickSelect — O(n) average time, O(1) space
    public int usingQuickSelect(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    private int quickSelect(int[] nums, int lo, int hi, int target) {
        int p = partition(nums, lo, hi);
        if (p == target) return nums[p];
        return p < target
            ? quickSelect(nums, p + 1, hi, target)
            : quickSelect(nums, lo, p - 1, target);
    }

    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i++, j);
            }
        }
        swap(nums, i, hi);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        KthSmallest sol = new KthSmallest();
        int[] nums = {7, 10, 4, 3, 20, 15};
        int k = 3;

        System.out.println("Array: [7, 10, 4, 3, 20, 15], k=" + k);
        System.out.println("Max-Heap:    " + sol.usingMaxHeap(nums.clone(), k));
        System.out.println("QuickSelect: " + sol.usingQuickSelect(nums.clone(), k));
    }
}
