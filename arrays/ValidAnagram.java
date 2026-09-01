package arrays;

// Problem (LC 242): Given two strings s and t, return true if t is an anagram
//          of s — same characters, same frequencies, any order.
// Example: s = "anagram", t = "nagaram" → true
//          s = "rat",     t = "car"     → false
// Approach: Character frequency array of size 26.
//   Increment for each char in s, decrement for each char in t.
//   If any count != 0, the strings differ.
// Time: O(n), Space: O(1)  (fixed 26-element array)
//
// ─────────────────────────────────────────────────────────────────────────────

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        for (char c : t.toCharArray()) count[c - 'a']--;
        for (int c : count) if (c != 0) return false;
        return true;
    }

    public static void main(String[] args) {
        ValidAnagram sol = new ValidAnagram();
        System.out.println(sol.isAnagram("anagram", "nagaram")); // true
        System.out.println(sol.isAnagram("rat", "car"));         // false
        System.out.println(sol.isAnagram("a", "a"));             // true
    }
}
