package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestElements {

    // Pair holds (distance, value) — makes heap comparator simple and explicit
    record Pair(int dist, int val) {}

    // Max-Heap by distance — O(n log k) time, O(k) space
    // Keep a max-heap of size k; if current element is closer, replace the farthest
    public int[] usingMaxHeap(int[] nums, int k, int target) {
        // Max-heap: farthest distance on top; break ties by larger value
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
            (a, b) -> a.dist != b.dist ? b.dist - a.dist : b.val - a.val
        );

        for (int num : nums) {
            maxHeap.offer(new Pair(Math.abs(num - target), num));
            if (maxHeap.size() > k) {
                maxHeap.poll(); // remove farthest
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = maxHeap.poll().val;
        }
        return result;
    }

    // Binary Search + Two Pointers — O(log n + k) time, O(1) space (sorted input)
    // Find the best window of size k using binary search on left boundary
    public int[] usingBinarySearch(int[] nums, int k, int target) {
        int lo = 0, hi = nums.length - k;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // Compare distances of left and right ends of the window
            if (target - nums[mid] > nums[mid + k] - target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return Arrays.copyOfRange(nums, lo, lo + k);
    }

    public static void main(String[] args) {
        KClosestElements sol = new KClosestElements();

        int[] nums = {1, 2, 3, 4, 5};
        int k = 3, target = 3;

        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("k=" + k + ", target=" + target);
        System.out.println("Max-Heap:      " + Arrays.toString(sol.usingMaxHeap(nums.clone(), k, target)));
        System.out.println("BinarySearch:  " + Arrays.toString(sol.usingBinarySearch(nums, k, target)));

        System.out.println();
        int[] nums2 = {1, 3, 6, 10, 15};
        int k2 = 3, target2 = 7;
        System.out.println("Array: " + Arrays.toString(nums2));
        System.out.println("k=" + k2 + ", target=" + target2);
        System.out.println("Max-Heap:      " + Arrays.toString(sol.usingMaxHeap(nums2.clone(), k2, target2)));
        System.out.println("BinarySearch:  " + Arrays.toString(sol.usingBinarySearch(nums2, k2, target2)));
    }
}
