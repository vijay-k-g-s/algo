package dynamicprogramming;

// Problem (LC 5): Given a string s, return the longest palindromic substring.
// Example: s = "babad" → "bab" (or "aba")
//          s = "cbbd"  → "bb"
// Approach: Expand Around Center.
//   For each index i, expand outward treating i as center of an odd-length palindrome,
//   and (i, i+1) as center of an even-length palindrome.
//   Track the longest expansion seen.
// Time: O(n²), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        int start = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            // Odd length (center at i)
            int len1 = expand(s, i, i);
            // Even length (center between i and i+1)
            int len2 = expand(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--; right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring sol = new LongestPalindromicSubstring();
        System.out.println(sol.longestPalindrome("babad")); // bab or aba
        System.out.println(sol.longestPalindrome("cbbd"));  // bb
        System.out.println(sol.longestPalindrome("a"));     // a
    }
}
