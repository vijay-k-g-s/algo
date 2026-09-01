package bitmanipulation;

// Problem (LC 190): Reverse the bits of a given 32-bit unsigned integer.
// Example: n = 00000010100101000001111010011100 → 964176192
//          (reversed: 00111001011110000010100101000000)
//          n = 11111111111111111111111111111101 → 3221225471
// Approach: Iterate 32 times.
//   At each step: take the lowest bit of n (n & 1), shift result left and OR it.
//   Right-shift n to process next bit.
// Time: O(1) — fixed 32 iterations. Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class ReverseBits {

    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1);
            n >>>= 1; // unsigned right shift
        }
        return result;
    }

    public static void main(String[] args) {
        ReverseBits sol = new ReverseBits();
        System.out.println(sol.reverseBits(0b00000010100101000001111010011100)); // 964176192
        System.out.println(Integer.toUnsignedString(sol.reverseBits(-3)));       // 3221225471
    }
}
