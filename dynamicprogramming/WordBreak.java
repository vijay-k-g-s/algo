package dynamicprogramming;

// Problem (LC 139): Given a string s and a dictionary of strings wordDict,
//          return true if s can be segmented into one or more dictionary words.
// Example: s = "leetcode", wordDict = ["leet","code"] → true
//          s = "applepenapple", wordDict = ["apple","pen"] → true
//          s = "catsandog", wordDict = ["cats","dog","sand","and","cat"] → false
// Approach: Bottom-up DP.
//   dp[i] = true if s[0..i-1] can be segmented using wordDict.
//   dp[0] = true (empty string).
//   For each i, check all j < i: if dp[j] is true and s[j..i-1] is in wordDict → dp[i] = true.
// Time: O(n² * L) where L = avg word length (substring check). Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        WordBreak sol = new WordBreak();
        System.out.println(sol.wordBreak("leetcode", List.of("leet","code")));          // true
        System.out.println(sol.wordBreak("applepenapple", List.of("apple","pen")));     // true
        System.out.println(sol.wordBreak("catsandog", List.of("cats","dog","sand","and","cat"))); // false
    }
}
