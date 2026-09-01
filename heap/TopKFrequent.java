package heap;

// Problem (LC 347): Given an integer array, return the k most frequently
//          occurring elements. The answer is guaranteed to be unique. Any order.
// Example: nums = [1, 1, 1, 2, 2, 3], k = 2
//          Output: [1, 2]  (1 appears 3×, 2 appears 2×)
//          nums = [1], k = 1
//          Output: [1]
// Approach 1: Min-Heap of size k keyed by frequency.
//   Build freq map, then maintain heap; evict least-frequent when size > k.
//   Time: O(n log k), Space: O(n)
// Approach 2: Bucket Sort — index = frequency (max = n), collect high → low.
//   Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {

    // Approach 1: Min-Heap of size k — O(n log k) time, O(n) space
    // Build frequency map, then maintain a min-heap keyed by frequency
    public int[] usingMinHeap(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.merge(num, 1, Integer::sum);
        }

        // Min-heap ordered by frequency (least frequent on top)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> freq.get(a) - freq.get(b)
        );

        for (int num : freq.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // remove least frequent
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }
        return result;
    }

    // Approach 2: Bucket Sort — O(n) time, O(n) space
    // Frequency can be at most n, so use index as frequency bucket
    public int[] usingBucketSort(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.merge(num, 1, Integer::sum);
        }

        // Bucket index = frequency, each bucket holds list of numbers
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int num : freq.keySet()) {
            int f = freq.get(num);
            if (buckets[f] == null) buckets[f] = new ArrayList<>();
            buckets[f].add(num);
        }

        // Collect top k from highest frequency buckets
        int[] result = new int[k];
        int idx = 0;
        for (int f = buckets.length - 1; f >= 1 && idx < k; f--) {
            if (buckets[f] != null) {
                for (int num : buckets[f]) {
                    if (idx < k) result[idx++] = num;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TopKFrequent sol = new TopKFrequent();

        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        System.out.println("Array: " + Arrays.toString(nums1) + ", k=" + k1);
        System.out.println("Min-Heap:    " + Arrays.toString(sol.usingMinHeap(nums1, k1)));
        System.out.println("BucketSort:  " + Arrays.toString(sol.usingBucketSort(nums1, k1)));

        System.out.println();
        int[] nums2 = {4, 4, 4, 6, 6, 3, 3, 3, 3, 1};
        int k2 = 2;
        System.out.println("Array: " + Arrays.toString(nums2) + ", k=" + k2);
        System.out.println("Min-Heap:    " + Arrays.toString(sol.usingMinHeap(nums2, k2)));
        System.out.println("BucketSort:  " + Arrays.toString(sol.usingBucketSort(nums2, k2)));
    }
}
