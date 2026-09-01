package bitmanipulation;

// Problem (LC 338): Given n, return an array ans of length n+1 where ans[i]
//          is the number of 1 bits in the binary representation of i.
// Example: n = 2 → [0, 1, 1]
//          n = 5 → [0, 1, 1, 2, 1, 2]
// Approach: DP using the relation:
//   ans[i] = ans[i >> 1] + (i & 1)
//   i >> 1 = i with last bit dropped (already computed).
//   i & 1 = whether the last bit is set.
// Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;

public class CountingBits {

    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        CountingBits sol = new CountingBits();
        System.out.println(Arrays.toString(sol.countBits(2))); // [0,1,1]
        System.out.println(Arrays.toString(sol.countBits(5))); // [0,1,1,2,1,2]
    }
}
