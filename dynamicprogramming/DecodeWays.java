package dynamicprogramming;

// Problem (LC 91): A message is encoded where 'A'=1, 'B'=2, ..., 'Z'=26.
//          Given a digit string, return the number of ways to decode it.
// Example: s = "12" → 2  ("AB"=1+2 or "L"=12)
//          s = "226" → 3  ("BZ"=2+26, "VF"=22+6, "BBF"=2+2+6)
//          s = "06"  → 0  (no valid decoding starting with 0)
// Approach: Bottom-up DP.
//   dp[i] = number of ways to decode s[0..i-1].
//   dp[0] = 1, dp[1] = 0 if s[0]=='0' else 1.
//   For each i from 2..n:
//     If s[i-1] != '0': dp[i] += dp[i-1]  (single digit decode)
//     If s[i-2..i-1] in 10..26: dp[i] += dp[i-2]  (two digit decode)
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class DecodeWays {

    public int numDecodings(String s) {
        int n = s.length();
        int prev2 = 1, prev1 = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            int curr = 0;
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (oneDigit >= 1) curr += prev1;
            if (twoDigit >= 10 && twoDigit <= 26) curr += prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public static void main(String[] args) {
        DecodeWays sol = new DecodeWays();
        System.out.println(sol.numDecodings("12"));  // 2
        System.out.println(sol.numDecodings("226")); // 3
        System.out.println(sol.numDecodings("06"));  // 0
        System.out.println(sol.numDecodings("11106")); // 2
    }
}
