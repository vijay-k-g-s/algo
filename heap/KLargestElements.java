package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KLargestElements {

    // Approach 1: Min-Heap of size k — O(n log k) time, O(k) space
    // Keep a min-heap; if current element > heap's min, replace it
    public int[] usingMinHeap(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }
        return result;
    }

    // Approach 2: QuickSelect — O(n) average time, O(1) space
    // Partition so that last k elements are the largest
    public int[] usingQuickSelect(int[] nums, int k) {
        quickSelect(nums, 0, nums.length - 1, nums.length - k);
        int[] result = Arrays.copyOfRange(nums, nums.length - k, nums.length);
        Arrays.sort(result); // optional: sort result for consistent output
        return result;
    }

    private void quickSelect(int[] nums, int lo, int hi, int target) {
        if (lo >= hi) return;
        int p = partition(nums, lo, hi);
        if (p == target) return;
        if (p < target) quickSelect(nums, p + 1, hi, target);
        else            quickSelect(nums, lo, p - 1, target);
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
        KLargestElements sol = new KLargestElements();
        int[] nums = {7, 10, 4, 3, 20, 15};
        int k = 3;

        System.out.println("Array: [7, 10, 4, 3, 20, 15], k=" + k);
        System.out.println("Min-Heap:    " + Arrays.toString(sol.usingMinHeap(nums.clone(), k)));
        System.out.println("QuickSelect: " + Arrays.toString(sol.usingQuickSelect(nums.clone(), k)));
    }
}
