package slidingwindow;

// Part 1 (LC 485): Find the maximum number of consecutive 1s in a binary array.
//   Example: nums = [1, 1, 0, 1, 1, 1] → 3  (trailing "1, 1, 1")
//   Approach: Reset left pointer to right+1 on every 0; track max window size.
//   Time: O(n), Space: O(1)
//
// Part 2 (LC 1004): Find the longest subarray of 1s you can get after flipping
//   at most k zeros. Equivalent to: longest subarray with at most k zeros.
//   Example: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2 → 6  (flip 2 zeros → [1,1,1,1,1,1])
//   Approach: Variable sliding window — track zero count; shrink from left when
//             zero count exceeds k.
//   Time: O(n), Space: O(1)

public class MaxConsecutiveOnes {

    // Part 1 — no flips, k = 0
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {                        // hit a zero, reset window
                left = right + 1;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // Part 2 — flip at most k zeros (variable window, longest)
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeroCount = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeroCount++;             // expand window

            while (zeroCount > k) {                        // shrink until at most k zeros
                if (nums[left] == 0) zeroCount--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnes solution = new MaxConsecutiveOnes();

        // Part 1
        System.out.println(solution.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1})); // Expected: 3
        System.out.println(solution.findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1})); // Expected: 2

        // Part 2
        System.out.println(solution.longestOnes(new int[]{1, 1, 0, 0, 0, 1, 1}, 0));      // Expected: 2
        System.out.println(solution.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2)); // Expected: 6
        System.out.println(solution.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3)); // Expected: 10
    }
}
