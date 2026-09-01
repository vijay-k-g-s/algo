package backtracking;

// Problem (LC 17): Given a string of digits (2–9), return all possible letter
//          combinations a phone keypad could produce. Return them in any order.
//          Phone mapping: 2→abc, 3→def, 4→ghi, 5→jkl, 6→mno, 7→pqrs, 8→tuv, 9→wxyz
// Example: digits = "23"
//          Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
//          digits = "2"
//          Output: ["a","b","c"]
//          digits = ""
//          Output: []
// Approach: For each digit index, iterate over its mapped letters.
//   Append a letter, recurse to the next digit index, then remove (backtrack).
//   When index reaches digits.length(), the current StringBuilder holds one full combo.
// Time: O(4^n * n) where n = number of digits (worst case 4 letters per digit). Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

    private static final Map<Character, String> PHONE = Map.of(
        '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
        '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz"
    );

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;
        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    // At each index, try every letter mapped to digits[index], then recurse to index+1
    private void backtrack(String digits, int index, StringBuilder current, List<String> result) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        for (char letter : PHONE.get(digits.charAt(index)).toCharArray()) {
            current.append(letter);
            backtrack(digits, index + 1, current, result);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinations sol = new LetterCombinations();

        System.out.println("\"23\" → " + sol.letterCombinations("23"));
        System.out.println("\"\"  → " + sol.letterCombinations(""));
        System.out.println("\"2\"  → " + sol.letterCombinations("2"));
        System.out.println("\"79\" → " + sol.letterCombinations("79"));
    }
}
