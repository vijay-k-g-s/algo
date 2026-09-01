package stack;

// Problem (LC 20): Given a string containing only '(', ')', '{', '}', '[', ']',
//          determine if the input string is valid.
//          Rules: open brackets must be closed by same type; closed in correct order.
// Example: "()" → true
//          "()[]{}" → true
//          "(]" → false
//          "([)]" → false
//          "{[]}" → true
// Approach: Stack.
//   For each char: if opening bracket → push its matching closer onto stack.
//   If closing bracket → pop from stack; if mismatch or stack empty → false.
//   At end, stack must be empty.
// Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else {
                if (stack.isEmpty() || stack.pop() != c) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses sol = new ValidParentheses();
        System.out.println(sol.isValid("()"));     // true
        System.out.println(sol.isValid("()[]{}")); // true
        System.out.println(sol.isValid("(]"));     // false
        System.out.println(sol.isValid("([)]"));   // false
        System.out.println(sol.isValid("{[]}"));   // true
    }
}
