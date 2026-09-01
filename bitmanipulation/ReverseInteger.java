package bitmanipulation;

// Problem (LC 7): Given a signed 32-bit integer x, return x with its digits reversed.
//          If reversing causes overflow (outside [-2³¹, 2³¹-1]), return 0.
// Example: x = 123  → 321
//          x = -123 → -321
//          x = 120  → 21  (trailing zero dropped)
//          x = 1534236469 → 0  (overflow)
// Approach: Extract digits with modulo; build reversed number.
//   Before multiplying result by 10, check if it would overflow.
//   Overflow condition: result > MAX/10 or (result == MAX/10 && digit > 7).
// Time: O(log x), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class ReverseInteger {

    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) return 0;
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && digit < -8)) return 0;
            result = result * 10 + digit;
        }
        return result;
    }

    public static void main(String[] args) {
        ReverseInteger sol = new ReverseInteger();
        System.out.println(sol.reverse(123));        // 321
        System.out.println(sol.reverse(-123));       // -321
        System.out.println(sol.reverse(120));        // 21
        System.out.println(sol.reverse(1534236469)); // 0
    }
}
