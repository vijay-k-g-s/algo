package slidingwindow;

// Problem (LC 3): Given a string s, find the length of the longest substring
//          that contains no duplicate characters.
// Example: s = "abcabcbb" → 3  (substring "abc")
//          s = "pwwkew"   → 3  (substring "wke")
// Approach: Variable sliding window with a HashSet tracking chars in the window.
//           Before expanding right, shrink from left until the duplicate is removed.
// Time: O(n), Space: O(min(n, charset size))

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeating {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;

        int left = 0;
        int maxLen = 0;
        Set<Character> window = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            while (window.contains(c)) {                   // shrink until no duplicate
                window.remove(s.charAt(left));
                left++;
            }

            window.add(c);                                 // expand window
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeating solution = new LongestSubstringWithoutRepeating();

        System.out.println(solution.lengthOfLongestSubstring("abcabcbb")); // Expected: 3 ("abc")
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));    // Expected: 1 ("b")
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));   // Expected: 3 ("wke")
        System.out.println(solution.lengthOfLongestSubstring(""));         // Expected: 0
    }
}
