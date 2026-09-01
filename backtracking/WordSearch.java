package backtracking;

// Problem (LC 79): Given an m×n grid of characters and a word string, return true
//          if the word exists in the grid. The word must be formed by sequentially
//          adjacent cells (horizontally or vertically). The same cell may not be
//          used more than once in a single path.
// Example: board = [['A','B','C','E'],
//                   ['S','F','C','S'],
//                   ['A','D','E','E']]
//          "ABCCED" → true   (A→B→C→C→E→D)
//          "SEE"    → true   (S→E→E, bottom-right area)
//          "ABCB"   → false  (would require reusing 'B')
//
//          board = [['a']], "a" → true
// Approach: Try starting DFS from every cell. At each step:
//   1. If index == word.length(), the word is fully matched → return true.
//   2. If out of bounds or cell doesn't match word.charAt(index) → return false.
//   3. Mark cell as visited by setting board[row][col] = '#'.
//   4. Recurse in all 4 directions for index+1.
//   5. Restore cell to its original character (backtrack).
// Time: O(m * n * 4^L) where L = word length. Space: O(L) recursion stack
//
// ─────────────────────────────────────────────────────────────────────────────

public class WordSearch {

    private static final int[] DR = {0, 0, 1, -1};
    private static final int[] DC = {1, -1, 0, 0};

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (backtrack(board, word, row, col, 0)) return true;
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, String word, int row, int col, int index) {
        if (index == word.length()) return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return false;
        if (board[row][col] != word.charAt(index)) return false;

        char temp = board[row][col];
        board[row][col] = '#'; // mark visited

        for (int d = 0; d < 4; d++) {
            if (backtrack(board, word, row + DR[d], col + DC[d], index + 1)) return true;
        }

        board[row][col] = temp; // restore (backtrack)
        return false;
    }

    public static void main(String[] args) {
        WordSearch sol = new WordSearch();

        char[][] board1 = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        System.out.println("\"ABCCED\" → " + sol.exist(board1, "ABCCED")); // true
        System.out.println("\"SEE\"    → " + sol.exist(board1, "SEE"));    // true
        System.out.println("\"ABCB\"   → " + sol.exist(board1, "ABCB"));   // false

        char[][] board2 = {{'a'}};
        System.out.println("\"a\"      → " + sol.exist(board2, "a"));      // true
    }
}
