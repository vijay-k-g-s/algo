package stack;

// Problem (LC 22): Given n pairs of parentheses, generate all combinations of
//          well-formed parentheses strings.
// Example: n = 3 → ["((()))","(()())","(())()","()(())","()()()"]
//          n = 1 → ["()"]
// Approach: Backtracking.
//   Track count of open and close brackets added so far.
//   Add '(' if open < n (still can open more).
//   Add ')' if close < open (can only close what's been opened).
//   Base case: open == close == n → valid combination found.
// Time: O(4^n / sqrt(n)) — nth Catalan number. Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(n, 0, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(int n, int open, int close, StringBuilder sb, List<String> result) {
        if (open == n && close == n) {
            result.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append('(');
            backtrack(n, open + 1, close, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            backtrack(n, open, close + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses sol = new GenerateParentheses();
        System.out.println(sol.generateParenthesis(1)); // [()]
        System.out.println(sol.generateParenthesis(3)); // [((())), (()()), (())(), ()(())), ()()()]
    }
}
