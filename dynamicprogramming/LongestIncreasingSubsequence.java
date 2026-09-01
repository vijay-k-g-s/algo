package dynamicprogramming;

// Problem (LC 300): Given an integer array, return the length of the longest
//          strictly increasing subsequence.
// Example: nums = [10,9,2,5,3,7,101,18] → 4  (2,3,7,101)
//          nums = [0,1,0,3,2,3] → 4
//          nums = [7,7,7,7] → 1
// Approach: Binary search (patience sorting) — O(n log n).
//   Maintain a 'tails' array where tails[i] = smallest tail of all IS of length i+1.
//   For each num, binary search for its position in tails:
//     - If larger than all tails → extend LIS by 1.
//     - Else replace tails[pos] with num (keeps tails as small as possible).
//   Length of tails array = length of LIS.
// Time: O(n log n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        for (int num : nums) {
            int lo = 0, hi = tails.size();
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tails.get(mid) < num) lo = mid + 1;
                else hi = mid;
            }
            if (lo == tails.size()) tails.add(num);
            else tails.set(lo, num);
        }
        return tails.size();
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence sol = new LongestIncreasingSubsequence();
        System.out.println(sol.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18})); // 4
        System.out.println(sol.lengthOfLIS(new int[]{0,1,0,3,2,3}));         // 4
        System.out.println(sol.lengthOfLIS(new int[]{7,7,7,7}));             // 1
    }
}
