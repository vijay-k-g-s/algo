package math;

// Problem (LC 50): Implement pow(x, n) — x raised to the power n.
//          n can be negative. Must run in O(log n).
// Example: x = 2.0, n = 10 → 1024.0
//          x = 2.1, n = 3  → 9.261
//          x = 2.0, n = -2 → 0.25  (1 / 2² = 0.25)
// Approach: Fast exponentiation (binary exponentiation).
//   If n is negative, compute 1 / pow(x, -n).
//   If n is odd: result = x * pow(x, n-1).
//   If n is even: result = pow(x*x, n/2).
//   Reduces exponent by half each step → O(log n).
// Time: O(log n), Space: O(log n) recursion stack
//
// ─────────────────────────────────────────────────────────────────────────────

public class PowXN {

    public double myPow(double x, int n) {
        long N = n; // use long to handle Integer.MIN_VALUE
        if (N < 0) { x = 1 / x; N = -N; }
        return fastPow(x, N);
    }

    private double fastPow(double x, long n) {
        if (n == 0) return 1.0;
        if (n % 2 == 1) return x * fastPow(x, n - 1);
        return fastPow(x * x, n / 2);
    }

    public static void main(String[] args) {
        PowXN sol = new PowXN();
        System.out.println(sol.myPow(2.0, 10));   // 1024.0
        System.out.println(sol.myPow(2.1, 3));    // 9.261000000000001
        System.out.println(sol.myPow(2.0, -2));   // 0.25
    }
}
