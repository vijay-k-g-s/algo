package backtracking;

// Problem (LC 51): Place n queens on an n×n chessboard so that no two queens
//          attack each other (no shared row, column, or diagonal). Return all
//          distinct board configurations. Each row in a board is a String of
//          'Q' (queen) and '.' (empty).
// Example: n = 4
//          Output: [[".Q..","...Q","Q...","..Q."],
//                   ["..Q.","Q...","...Q",".Q.."]]
//          n = 1 → [["Q"]]
// Approach: Place exactly one queen per row using DFS.
//   Track three attack sets to check in O(1):
//     cols:    column index (constant down a column)
//     diag:    row - col   (constant along top-left → bottom-right diagonal)
//     antiDiag: row + col  (constant along top-right → bottom-left diagonal)
//   For each column in the current row, skip if any set contains its key.
//   Undo additions on backtrack.
// Time: O(n!), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        backtrack(0, n, new int[n], new HashSet<>(), new HashSet<>(), new HashSet<>(), result);
        return result;
    }

    // Place one queen per row; track attacked columns and diagonals
    // col:      same column          → col
    // diagonal: top-left to bottom-right → row - col (constant along diagonal)
    // antiDiag: top-right to bottom-left → row + col (constant along anti-diagonal)
    private void backtrack(int row, int n, int[] queens,
                           Set<Integer> cols, Set<Integer> diag, Set<Integer> antiDiag,
                           List<List<String>> result) {
        if (row == n) {
            result.add(buildBoard(queens, n));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (cols.contains(col) || diag.contains(row - col) || antiDiag.contains(row + col)) {
                continue; // column or diagonal is under attack
            }
            queens[row] = col;
            cols.add(col);
            diag.add(row - col);
            antiDiag.add(row + col);

            backtrack(row + 1, n, queens, cols, diag, antiDiag, result);

            queens[row] = -1;
            cols.remove(col);
            diag.remove(row - col);
            antiDiag.remove(row + col);
        }
    }

    private List<String> buildBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            char[] line = new char[n];
            for (int col = 0; col < n; col++) {
                line[col] = (col == queens[row]) ? 'Q' : '.';
            }
            board.add(new String(line));
        }
        return board;
    }

    public static void main(String[] args) {
        NQueens sol = new NQueens();

        List<List<String>> result4 = sol.solveNQueens(4);
        System.out.println("N=4, solutions: " + result4.size());
        for (List<String> board : result4) {
            board.forEach(System.out::println);
            System.out.println();
        }

        System.out.println("N=1, solutions: " + sol.solveNQueens(1).size());
        System.out.println("N=3, solutions: " + sol.solveNQueens(3).size());
        System.out.println("N=8, solutions: " + sol.solveNQueens(8).size());
    }
}
