package bitmanipulation;

// Problem (LC 191): Given a positive integer n, return the number of set bits
//          (1s) in its binary representation. Also known as "Hamming weight".
// Example: n = 11 (binary: 1011)  → 3
//          n = 128 (binary: 10000000) → 1
//          n = 2147483645 (binary: 1111111111111111111111111111101) → 30
// Approach: n & (n-1) clears the lowest set bit.
//   Count how many times we can do this before n becomes 0.
// Time: O(k) where k = number of set bits. Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class NumberOf1Bits {

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1); // clear lowest set bit
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOf1Bits sol = new NumberOf1Bits();
        System.out.println(sol.hammingWeight(11));         // 3 (1011)
        System.out.println(sol.hammingWeight(128));        // 1 (10000000)
        System.out.println(sol.hammingWeight(0b11111111)); // 8
    }
}
