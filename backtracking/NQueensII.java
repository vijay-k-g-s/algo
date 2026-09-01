package backtracking;

// Problem (LC 52): The n-queens puzzle. Return the number of distinct solutions
//          to placing n queens on an n×n board so no two queens attack each other.
//          (Same as NQueens.java but returns only the count, not the boards.)
// Example: n = 4 → 2
//          n = 1 → 1
// Approach: Backtracking with 3 attack-tracking sets — same as NQueens.java.
//   cols: attacked columns.
//   diag: attacked diagonals (row - col is constant).
//   antiDiag: attacked anti-diagonals (row + col is constant).
//   Place one queen per row; increment count when row == n.
// Time: O(n!), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.HashSet;
import java.util.Set;

public class NQueensII {

    public int totalNQueens(int n) {
        return backtrack(0, n, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    private int backtrack(int row, int n, Set<Integer> cols, Set<Integer> diag, Set<Integer> antiDiag) {
        if (row == n) return 1;
        int count = 0;
        for (int col = 0; col < n; col++) {
            if (cols.contains(col) || diag.contains(row - col) || antiDiag.contains(row + col)) continue;
            cols.add(col); diag.add(row - col); antiDiag.add(row + col);
            count += backtrack(row + 1, n, cols, diag, antiDiag);
            cols.remove(col); diag.remove(row - col); antiDiag.remove(row + col);
        }
        return count;
    }

    public static void main(String[] args) {
        NQueensII sol = new NQueensII();
        System.out.println(sol.totalNQueens(1)); // 1
        System.out.println(sol.totalNQueens(4)); // 2
        System.out.println(sol.totalNQueens(8)); // 92
    }
}
