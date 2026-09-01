package heap;

// Problem (LC 1636): Sort an array in increasing order based on element frequency.
//          If two numbers have the same frequency, sort them in decreasing order of value.
// Example: nums = [1, 1, 2, 2, 2, 3]
//          Output: [3, 1, 1, 2, 2, 2]  (3 appears once, 1 twice, 2 three times)
//          nums = [2, 3, 1, 3, 2, 4, 2, 3, 1]
//          Output: [4, 1, 1, 3, 3, 3, 2, 2, 2]
// Approach 1: Max-Heap ordered by (frequency desc, value asc) — O(n log n)
// Approach 2: Bucket Sort — bucket index = frequency, collect high → low — O(n)
// Time: O(n log n) heap / O(n) bucket. Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;

public class FrequencySort {

    // Approach 1: Max-Heap — O(n log n) time, O(n) space
    // Build freq map, push unique elements into max-heap ordered by frequency
    public int[] usingMaxHeap(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) freq.merge(num, 1, Integer::sum);

        // Max-heap: higher freq first; break ties by smaller value first
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            (a, b) -> freq.get(a).equals(freq.get(b)) ? a - b : freq.get(b) - freq.get(a)
        );
        maxHeap.addAll(freq.keySet());

        int[] result = new int[nums.length];
        int idx = 0;
        while (!maxHeap.isEmpty()) {
            int num = maxHeap.poll();
            int count = freq.get(num);
            while (count-- > 0) result[idx++] = num;
        }
        return result;
    }

    // Approach 2: Bucket Sort — O(n) time, O(n) space
    // Bucket index = frequency; collect from highest bucket down
    public int[] usingBucketSort(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) freq.merge(num, 1, Integer::sum);

        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int f = e.getValue();
            if (buckets[f] == null) buckets[f] = new ArrayList<>();
            buckets[f].add(e.getKey());
        }

        int[] result = new int[nums.length];
        int idx = 0;
        for (int f = buckets.length - 1; f >= 1; f--) {
            if (buckets[f] == null) continue;
            buckets[f].sort(null); // sort within same frequency
            for (int num : buckets[f]) {
                for (int c = 0; c < f; c++) result[idx++] = num;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FrequencySort sol = new FrequencySort();

        int[] nums1 = {1, 1, 2, 2, 2, 3};
        System.out.println("Input:      " + Arrays.toString(nums1));
        System.out.println("Max-Heap:   " + Arrays.toString(sol.usingMaxHeap(nums1.clone())));
        System.out.println("BucketSort: " + Arrays.toString(sol.usingBucketSort(nums1.clone())));

        System.out.println();
        int[] nums2 = {2, 3, 1, 3, 2, 4, 2, 3, 1};
        System.out.println("Input:      " + Arrays.toString(nums2));
        System.out.println("Max-Heap:   " + Arrays.toString(sol.usingMaxHeap(nums2.clone())));
        System.out.println("BucketSort: " + Arrays.toString(sol.usingBucketSort(nums2.clone())));
    }
}
