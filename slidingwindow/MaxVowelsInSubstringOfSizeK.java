package slidingwindow;

// Problem (LC 1456): Given a string s and integer k, return the maximum number
//          of vowels in any substring of s with length exactly k.
// Example: s = "abciiidef", k = 3 → 3  (substring "iii")
//          s = "leetcode",  k = 3 → 2  (substring "lee" or "eet")
// Approach: Fixed sliding window — increment vowel count when right char is a
//           vowel; decrement when the outgoing left char was a vowel.
// Time: O(n), Space: O(1)

public class MaxVowelsInSubstringOfSizeK {

    private boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }

    public int maxVowels(String s, int k) {
        if (s == null || s.length() < k) {
            throw new IllegalArgumentException("String length must be >= k");
        }

        int left = 0;
        int windowCount = 0;
        int maxCount = 0;

        for (int right = 0; right < s.length(); right++) {
            if (isVowel(s.charAt(right))) windowCount++;   // expand window

            if (right - left + 1 == k) {                   // window is full
                maxCount = Math.max(maxCount, windowCount);
                if (isVowel(s.charAt(left))) windowCount--; // shrink window
                left++;
            }
        }

        return maxCount;
    }

    public static void main(String[] args) {
        MaxVowelsInSubstringOfSizeK solution = new MaxVowelsInSubstringOfSizeK();

        System.out.println(solution.maxVowels("abciiidef", 3)); // Expected: 3 ("iii")
        System.out.println(solution.maxVowels("aeiou", 2));     // Expected: 2
        System.out.println(solution.maxVowels("leetcode", 3));  // Expected: 2 ("lee","eet")
    }
}
