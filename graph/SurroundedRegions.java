package graph;

// LeetCode 130. Surrounded Regions
// Any 'O' connected (directly or indirectly) to a border cell can never be captured.
// Mark those safe 'O's first, then flip everything else.
public class SurroundedRegions {

    private int rows, cols;

    // DFS from a border 'O' — marks every reachable 'O' as 'S' (safe)
    private void dfs(char[][] board, int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || board[r][c] != 'O') return;
        board[r][c] = 'S';
        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);
    }

    public void solve(char[][] board) {
        rows = board.length;
        cols = board[0].length;

        // Step 1: mark all 'O's reachable from any border cell as 'S'
        for (int r = 0; r < rows; r++) {
            dfs(board, r, 0);
            dfs(board, r, cols - 1);
        }
        for (int c = 0; c < cols; c++) {
            dfs(board, 0, c);
            dfs(board, rows - 1, c);
        }

        // Step 2: flip surrounded 'O' → 'X', restore safe 'S' → 'O'
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') board[r][c] = 'X';
                else if (board[r][c] == 'S') board[r][c] = 'O';
            }
        }
    }

    public static void main(String[] args) {
        SurroundedRegions solution = new SurroundedRegions();

        char[][] board = {
            {'X', 'X', 'X', 'X'},
            {'X', 'O', 'O', 'X'},
            {'X', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'X'}
        };

        solution.solve(board);

        for (char[] row : board) {
            System.out.println(new String(row));
        }
        // Expected:
        // XXXX
        // XXXX
        // XXXX
        // XOXX
    }
}
