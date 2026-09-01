package greedy;

// Problem (LC 55): You are given an array where nums[i] represents the maximum
//          jump length from index i. Determine if you can reach the last index.
// Example: nums = [2,3,1,1,4] → true  (jump 1→3→4 or 1→4)
//          nums = [3,2,1,0,4] → false (always land on index 3 which has jump 0)
// Approach: Greedy — track the furthest index reachable.
//   At each index i, update maxReach = max(maxReach, i + nums[i]).
//   If i > maxReach at any point, we're stuck → return false.
//   If maxReach >= last index → return true.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class JumpGame {

    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        JumpGame sol = new JumpGame();
        System.out.println(sol.canJump(new int[]{2, 3, 1, 1, 4})); // true
        System.out.println(sol.canJump(new int[]{3, 2, 1, 0, 4})); // false
        System.out.println(sol.canJump(new int[]{0}));              // true
    }
}
