package math;

// Problem (LC 43): Given two non-negative integers as strings num1 and num2,
//          return their product as a string. Do not use BigInteger or convert directly.
// Example: num1 = "2", num2 = "3" → "6"
//          num1 = "123", num2 = "456" → "56088"
// Approach: Grade-school multiplication.
//   Result of num1[i] * num2[j] goes to positions [i+j] and [i+j+1] in result array.
//   Process all digit pairs, accumulate carries.
//   Build result string from array, skipping leading zeros.
// Time: O(m*n), Space: O(m+n)
//
// ─────────────────────────────────────────────────────────────────────────────

public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];
                pos[p2] = sum % 10;
                pos[p1] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            if (!(sb.length() == 0 && p == 0)) sb.append(p);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        MultiplyStrings sol = new MultiplyStrings();
        System.out.println(sol.multiply("2", "3"));    // 6
        System.out.println(sol.multiply("123", "456")); // 56088
        System.out.println(sol.multiply("0", "456"));  // 0
    }
}
