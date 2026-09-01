package slidingwindow;

// Problem (LC 567): Given strings s1 and s2, return true if s2 contains a
//          permutation of s1 (i.e., s2 contains a substring that is an anagram of s1).
// Example: s1 = "ab", s2 = "eidbaooo" → true  ("ba" is a permutation of "ab")
//          s1 = "ab", s2 = "eidboaoo" → false
// Approach: Fixed sliding window of size len(s1) over s2.
//   Maintain frequency arrays for s1 and the current window.
//   Track number of characters with matching frequencies (`matches`).
//   Slide the window: add right char, remove left char, update matches.
//   If matches == 26 → found a permutation.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] freq1 = new int[26], freq2 = new int[26];
        for (char c : s1.toCharArray()) freq1[c - 'a']++;

        int k = s1.length();
        // Initialize first window
        for (int i = 0; i < k; i++) freq2[s2.charAt(i) - 'a']++;

        int matches = 0;
        for (int i = 0; i < 26; i++) if (freq1[i] == freq2[i]) matches++;

        for (int right = k; right < s2.length(); right++) {
            if (matches == 26) return true;

            // Add right char
            int in = s2.charAt(right) - 'a';
            freq2[in]++;
            if (freq2[in] == freq1[in]) matches++;
            else if (freq2[in] - 1 == freq1[in]) matches--;

            // Remove left char
            int out = s2.charAt(right - k) - 'a';
            freq2[out]--;
            if (freq2[out] == freq1[out]) matches++;
            else if (freq2[out] + 1 == freq1[out]) matches--;
        }
        return matches == 26;
    }

    public static void main(String[] args) {
        PermutationInString sol = new PermutationInString();
        System.out.println(sol.checkInclusion("ab", "eidbaooo")); // true
        System.out.println(sol.checkInclusion("ab", "eidboaoo")); // false
        System.out.println(sol.checkInclusion("adc", "dcda"));    // true
    }
}
