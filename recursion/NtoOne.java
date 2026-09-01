package recursion;

// Problem: Print numbers from 1 to N using recursion (ascending order).
// Example: N = 5 → prints: 1 2 3 4 5
// Approach: IBH (Induction-Base-Hypothesis) method.
//   - Hypothesis: helper(n-1) prints 1..n-1 correctly.
//   - Base case: n == 1 → print 1 and return.
//   - Induction: after helper(n-1) returns, print n to extend the sequence.
//   Recurse BEFORE printing → numbers print in ascending order (1 to N).
// Time: O(n), Space: O(n) recursion stack
//
// ─────────────────────────────────────────────────────────────────────────────

public class NtoOne {

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