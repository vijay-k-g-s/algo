package slidingwindow;

// Problem (LC 209): Given an array of positive integers and a target, find the
//          minimum length contiguous subarray whose sum is >= target.
//          Return 0 if no such subarray exists.
// Example: target = 7, arr = [2, 3, 1, 2, 4, 3] → 2  (subarray [4, 3])
//          target = 4, arr = [1, 4, 4]           → 1  (subarray [4])
// Approach: Variable sliding window. Expand right; once sum >= target, record
//           the window length and shrink from left as long as condition holds.
// Time: O(n), Space: O(1)

public class MinSizeSubarraySum {

    public int minSubArrayLen(int target, int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int left = 0;
        int windowSum = 0;
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < arr.length; right++) {
            windowSum += arr[right];                        // expand window

            while (windowSum >= target) {                   // shrink as much as possible
                minLen = Math.min(minLen, right - left + 1);
                windowSum -= arr[left];
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        MinSizeSubarraySum solution = new MinSizeSubarraySum();

        System.out.println(solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3})); // Expected: 2 ([4,3])
        System.out.println(solution.minSubArrayLen(4, new int[]{1, 4, 4}));           // Expected: 1 ([4])
        System.out.println(solution.minSubArrayLen(11, new int[]{1, 1, 1, 1, 1}));   // Expected: 0 (no subarray)
    }
}
