package bitmanipulation;

// Problem (LC 371): Calculate the sum of two integers a and b without using + or -.
// Example: a = 1, b = 2 → 3
//          a = 2, b = 3 → 5
// Approach: Bit manipulation.
//   XOR gives sum without carry: a ^ b.
//   AND + left-shift gives carry: (a & b) << 1.
//   Repeat until no carry remains (b == 0).
// Time: O(1) — at most 32 iterations. Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class SumOfTwoIntegers {

    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1; // carry
            a = a ^ b;                 // sum without carry
            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        SumOfTwoIntegers sol = new SumOfTwoIntegers();
        System.out.println(sol.getSum(1, 2));   // 3
        System.out.println(sol.getSum(2, 3));   // 5
        System.out.println(sol.getSum(-1, 1));  // 0
    }
}
