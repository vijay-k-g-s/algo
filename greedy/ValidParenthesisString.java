package greedy;

// Problem (LC 678): Given a string containing '(', ')', and '*', where '*' can be
//          treated as '(', ')', or empty, determine if the string is valid.
// Example: s = "()" → true
//          s = "(*)" → true
//          s = "(*))" → true
//          s = "(*())" → true
// Approach: Greedy with [lo, hi] range of possible open parenthesis counts.
//   lo = minimum possible unmatched '(' (treat '*' as ')' or empty).
//   hi = maximum possible unmatched '(' (treat '*' as '(').
//   For each char:
//     '(' → lo++, hi++
//     ')' → lo--, hi--
//     '*' → lo--, hi++  (can be ')' (lo-1) or '(' (hi+1))
//   If hi < 0 → too many ')' even with all '*' as '(' → invalid.
//   lo = max(lo, 0) to avoid going negative (excess ')' can be handled by '*').
//   Valid if lo == 0 at end.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class ValidParenthesisString {

    public boolean checkValidString(String s) {
        int lo = 0, hi = 0;
        for (char c : s.toCharArray()) {
            if (c == '(')      { lo++; hi++; }
            else if (c == ')') { lo--; hi--; }
            else               { lo--; hi++; } // '*'
            if (hi < 0) return false;  // too many ')'
            lo = Math.max(lo, 0);      // can't have negative open count
        }
        return lo == 0;
    }

    public static void main(String[] args) {
        ValidParenthesisString sol = new ValidParenthesisString();
        System.out.println(sol.checkValidString("()"));     // true
        System.out.println(sol.checkValidString("(*)"));    // true
        System.out.println(sol.checkValidString("(*))"));   // true
        System.out.println(sol.checkValidString("((*"));;   // false
    }
}
