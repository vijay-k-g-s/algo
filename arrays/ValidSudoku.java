package arrays;

// Problem (LC 36): Determine if a 9x9 Sudoku board is valid. Only filled cells
//          need to be validated (board may be partially filled with '.').
//          Rules: each row, column, and 3×3 box must contain digits 1-9 with no repetition.
// Example: A board with no repeated digits in any row, column, or 3×3 box → true
//          A board with '8' twice in the same row → false
// Approach: Use three sets of HashSets — rows[9], cols[9], boxes[9].
//   For each filled cell (r, c) with digit d:
//     box index = (r/3)*3 + (c/3)
//     If d is already in rows[r], cols[c], or boxes[box] → invalid.
//     Otherwise add d to all three sets.
// Time: O(1) — fixed 9×9 board. Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char d = board[r][c];
                if (d == '.') continue;
                // Encode the position type to make keys unique across rows/cols/boxes
                if (!seen.add(d + " in row " + r)) return false;
                if (!seen.add(d + " in col " + c)) return false;
                if (!seen.add(d + " in box " + (r / 3) + "-" + (c / 3))) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidSudoku sol = new ValidSudoku();

        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(sol.isValidSudoku(board)); // true

        board[0][0] = '8'; // duplicate 8 in row 0 and col 0
        System.out.println(sol.isValidSudoku(board)); // false
    }
}
