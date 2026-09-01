package dynamicprogramming;

// Problem (LC 494): Given an integer array and a target, assign '+' or '-' to
//          each element. Return the number of ways to make the expression equal target.
// Example: nums = [1, 1, 1, 1, 1], target = 3 → 5
//          (+1+1+1+1-1), (+1+1+1-1+1), (+1+1-1+1+1), (+1-1+1+1+1), (-1+1+1+1+1)
//          nums = [1], target = 1 → 1
// Approach: DP on achievable sums.
//   Use a HashMap: sum → number of ways to reach that sum.
//   For each num, update each reachable sum by adding and subtracting num.
// Time: O(n * totalSum), Space: O(totalSum)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.HashMap;
import java.util.Map;

public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1);
        for (int num : nums) {
            Map<Integer, Integer> next = new HashMap<>();
            for (Map.Entry<Integer, Integer> e : dp.entrySet()) {
                int sum = e.getKey(), ways = e.getValue();
                next.merge(sum + num, ways, Integer::sum);
                next.merge(sum - num, ways, Integer::sum);
            }
            dp = next;
        }
        return dp.getOrDefault(target, 0);
    }

    public static void main(String[] args) {
        TargetSum sol = new TargetSum();
        System.out.println(sol.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3)); // 5
        System.out.println(sol.findTargetSumWays(new int[]{1}, 1));             // 1
        System.out.println(sol.findTargetSumWays(new int[]{1, 0}, 1));          // 2
    }
}
