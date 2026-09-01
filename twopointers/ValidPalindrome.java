package twopointers;

// Problem (LC 125): A phrase is a palindrome if, after converting all uppercase
//          letters to lowercase and removing all non-alphanumeric characters,
//          it reads the same forward and backward.
// Example: "A man, a plan, a canal: Panama" → true  ("amanaplanacanalpanama")
//          "race a car"                     → false ("raceacar")
//          " "                              → true  (empty after stripping)
// Approach: Two pointers (left, right) on the original string.
//   Skip non-alphanumeric characters from both ends.
//   Compare lowercase versions of the characters at both pointers.
//   If any mismatch found → false.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left)))  left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome sol = new ValidPalindrome();
        System.out.println(sol.isPalindrome("A man, a plan, a canal: Panama")); // true
        System.out.println(sol.isPalindrome("race a car"));                     // false
        System.out.println(sol.isPalindrome(" "));                              // true
    }
}
