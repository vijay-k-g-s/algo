package slidingwindow;

// Problem (LC 424): Given a string s and integer k, you can replace at most k
//          characters in any window with any letter. Find the length of the
//          longest substring containing the same letter after replacements.
// Example: s = "ABAB", k = 2 → 4  (replace both A's or B's → "AAAA" or "BBBB")
//          s = "AABABBA", k = 1 → 4  (replace one B in "ABAB" or "BABB" → "AAAB" or "BBBB")
// Approach: Variable sliding window with a frequency array.
//   windowSize - maxFreq <= k means the window is valid (can replace the rest).
//   When invalid, shrink from left (decrement freq of left char, advance left).
//   maxFreq only ever increases (we never need to shrink maxFreq — this is safe
//   because a smaller maxFreq can never produce a longer valid window).
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0, maxFreq = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, count[s.charAt(right) - 'A']);

            // Window size - max frequency = characters that need replacing
            while ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement sol = new LongestRepeatingCharacterReplacement();
        System.out.println(sol.characterReplacement("ABAB", 2));    // 4
        System.out.println(sol.characterReplacement("AABABBA", 1)); // 4
        System.out.println(sol.characterReplacement("AAAA", 0));    // 4
    }
}
