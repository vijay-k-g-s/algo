package slidingwindow;

// Problem: Given a string s and integer k, find the length of the longest
//          substring with at most k distinct characters.
// Example: s = "araaci", k = 2 → 4  (substring "araa" has 2 distinct: 'a', 'r')
//          s = "cbbebi",  k = 3 → 5  (substring "bbebi" has 3 distinct: 'b', 'e', 'i')
// Approach: Variable sliding window with a frequency map. Shrink from left
//           whenever the number of distinct characters exceeds k.
// Time: O(n), Space: O(k)

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithKDistinct {

    public int longestSubstringKDistinct(String s, int k) {
        if (s == null || s.isEmpty() || k == 0) return 0;

        int left = 0;
        int maxLen = 0;
        Map<Character, Integer> freq = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0) + 1);     // expand window

            while (freq.size() > k) {                      // shrink until k distinct
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);
                if (freq.get(leftChar) == 0) freq.remove(leftChar);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubstringWithKDistinct solution = new LongestSubstringWithKDistinct();

        System.out.println(solution.longestSubstringKDistinct("araaci", 2)); // Expected: 4 ("araa")
        System.out.println(solution.longestSubstringKDistinct("araaci", 1)); // Expected: 2 ("aa")
        System.out.println(solution.longestSubstringKDistinct("cbbebi", 3)); // Expected: 5 ("bbebi")
    }
}
