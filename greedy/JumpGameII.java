package greedy;

// Problem (LC 45): Given nums[i] = max jump from index i, return the minimum
//          number of jumps to reach the last index. Guaranteed to be reachable.
// Example: nums = [2,3,1,1,4] → 2  (0→1→4 using jumps of 1 and 3)
//          nums = [2,3,0,1,4] → 2
// Approach: Greedy — BFS-like level-by-level expansion.
//   Track the current jump boundary and the furthest reachable index.
//   When we reach the current boundary, we must make another jump.
//   The next boundary = furthest reachable index seen in this "level".
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class JumpGameII {

    public int jump(int[] nums) {
        int jumps = 0, curEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == curEnd) {  // reached end of current jump level
                jumps++;
                curEnd = farthest;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        JumpGameII sol = new JumpGameII();
        System.out.println(sol.jump(new int[]{2, 3, 1, 1, 4})); // 2
        System.out.println(sol.jump(new int[]{2, 3, 0, 1, 4})); // 2
        System.out.println(sol.jump(new int[]{1}));              // 0
    }
}
