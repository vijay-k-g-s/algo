package dynamicprogramming;

// Problem (LC 647): Given a string s, return the number of palindromic substrings.
//          A substring is palindromic if it reads the same forward and backward.
// Example: s = "abc" → 3  ("a","b","c" — each single char is a palindrome)
//          s = "aaa" → 6  ("a","a","a","aa","aa","aaa")
// Approach: Expand around center (same as LongestPalindromicSubstring).
//   For each index i, expand outward for both odd and even length palindromes.
//   Count every valid expansion.
// Time: O(n²), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += expand(s, i, i);     // odd length
            count += expand(s, i, i + 1); // even length
        }
        return count;
    }

    private int expand(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--; right++;
        }
        return count;
    }

    public static void main(String[] args) {
        PalindromicSubstrings sol = new PalindromicSubstrings();
        System.out.println(sol.countSubstrings("abc")); // 3
        System.out.println(sol.countSubstrings("aaa")); // 6
        System.out.println(sol.countSubstrings("a"));   // 1
    }
}
