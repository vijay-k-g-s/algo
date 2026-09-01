package slidingwindow;

// Problem (LC 76): Given strings s and t, return the minimum window substring
//          of s that contains every character in t (including duplicates).
//          Return "" if no such window exists.
// Example: s = "ADOBECODEBANC", t = "ABC" → "BANC"
//          s = "a", t = "a"               → "a"
//          s = "a", t = "aa"              → ""
// Approach: Variable sliding window with a frequency map.
//   need = distinct chars in t still required. have = distinct chars satisfied.
//   Expand right: add s[right] to window; if its frequency matches t's, have++.
//   When have == need (valid window): try to shrink from left to minimize length.
//     Remove s[left] from window; if its freq drops below t's, have--.
//   Track the smallest valid window seen.
// Time: O(|s| + |t|), Space: O(|t|)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (t.isEmpty()) return "";

        Map<Character, Integer> tFreq = new HashMap<>();
        for (char c : t.toCharArray()) tFreq.merge(c, 1, Integer::sum);

        Map<Character, Integer> window = new HashMap<>();
        int have = 0, need = tFreq.size();
        int left = 0, minLen = Integer.MAX_VALUE, minLeft = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            window.merge(c, 1, Integer::sum);
            if (tFreq.containsKey(c) && window.get(c).equals(tFreq.get(c))) have++;

            while (have == need) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }
                char lc = s.charAt(left);
                window.merge(lc, -1, Integer::sum);
                if (tFreq.containsKey(lc) && window.get(lc) < tFreq.get(lc)) have--;
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring sol = new MinimumWindowSubstring();
        System.out.println(sol.minWindow("ADOBECODEBANC", "ABC")); // "BANC"
        System.out.println(sol.minWindow("a", "a"));               // "a"
        System.out.println(sol.minWindow("a", "aa"));              // ""
    }
}
