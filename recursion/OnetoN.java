package recursion;

// Problem: Print numbers from N down to 1 using recursion (descending order).
// Example: N = 5 → prints: 5 4 3 2 1
// Approach: IBH (Induction-Base-Hypothesis) method.
//   - Hypothesis: helper(n-1) prints n-1..1 correctly.
//   - Base case: n == 1 → print 1 and return.
//   - Induction: print n BEFORE recursing so n appears first in the output.
//   Print BEFORE recursing → numbers print in descending order (N to 1).
// Time: O(n), Space: O(n) recursion stack
//
// ─────────────────────────────────────────────────────────────────────────────

public class OnetoN {

    public static void main(String[] args) {

        int n = 5;
         
        helper(n);

    }

    private static void helper(int n) {
        // Base
        if (n == 1) {
            System.out.println(n);
            return;
        }

        // hypotehesis 
        helper(n - 1);

        // Induction
        System.out.println(n);
        
    }
    
}
