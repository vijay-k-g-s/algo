package backtracking;

// Problem (LC 37): Fill the empty cells (marked '.') of a partially-filled 9×9
//          Sudoku board so that every row, column, and 3×3 sub-box contains the
//          digits 1–9 exactly once. The input is guaranteed to have exactly one solution.
// Example: Input board (partial):
//          5 3 . | . 7 . | . . .
//          6 . . | 1 9 5 | . . .
//          . 9 8 | . . . | . 6 .
//          ------+-------+------
//          8 . . | . 6 . | . . 3
//          4 . . | 8 . 3 | . . 1
//          7 . . | . 2 . | . . 6
//          ------+-------+------
//          . 6 . | . . . | 2 8 .
//          . . . | 4 1 9 | . . 5
//          . . . | . 8 . | . 7 9
//
//          Output: the fully solved board with all '.' cells replaced.
// Approach: Scan row-by-row, cell-by-cell for the first empty ('.') cell.
//   Try placing digits '1'–'9'. For each digit, check row, column, and 3×3 box
//   constraints via isValid(). If valid, place and recurse. If recursion returns
//   false (dead end), reset to '.' and try next digit.
//   Return true when no empty cell remains (board is solved).
//   Box index: topRow = (row/3)*3, topCol = (col/3)*3; iterate i in 0..8.
// Time: O(9^m) where m = number of empty cells. Space: O(m) recursion stack
//
// ─────────────────────────────────────────────────────────────────────────────

public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != '.') continue;
                for (char c = '1'; c <= '9'; c++) {
                    if (isValid(board, row, col, c)) {
                        board[row][col] = c;
                        if (backtrack(board)) return true;
                        board[row][col] = '.'; // backtrack
                    }
                }
                return false; // no digit worked for this cell
            }
        }
        return true; // no empty cell left — solved
    }

    // Check if placing c at (row, col) violates any Sudoku constraint
    private boolean isValid(char[][] board, int row, int col, char c) {
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) return false;           // same row
            if (board[i][col] == c) return false;           // same col
            if (board[boxRow + i / 3][boxCol + i % 3] == c) return false; // same 3x3 box
        }
        return true;
    }

    private void printBoard(char[][] board) {
        for (int r = 0; r < 9; r++) {
            if (r % 3 == 0) System.out.println("+-------+-------+-------+");
            for (int c = 0; c < 9; c++) {
                if (c % 3 == 0) System.out.print("| ");
                System.out.print(board[r][c] + " ");
            }
            System.out.println("|");
        }
        System.out.println("+-------+-------+-------+");
    }

    public static void main(String[] args) {
        SudokuSolver sol = new SudokuSolver();

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

        System.out.println("Before:");
        sol.printBoard(board);
        sol.solveSudoku(board);
        System.out.println("\nAfter:");
        sol.printBoard(board);
    }
}
