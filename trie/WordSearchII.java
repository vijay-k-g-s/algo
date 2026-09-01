package trie;

// Problem (LC 212): Given an m×n board of characters and a list of words, return
//          all words that exist in the board. A word can be constructed from
//          sequentially adjacent cells (horizontally or vertically). A cell
//          may not be reused in the same word.
// Example: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
//          words = ["oath","pea","eat","rain"] → ["eat","oath"]
// Approach: Build a Trie of all words. DFS from every cell on the board.
//   At each step, follow Trie edges. If a TrieNode is marked end → found a word.
//   Mark visited cells with '#', restore after DFS (backtrack).
//   Prune: remove found words from Trie to avoid duplicates.
// Time: O(M*N*4^L) where L = max word length. Space: O(total chars in words)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // non-null when a word ends here
    }

    private static final int[] DR = {0, 0, 1, -1};
    private static final int[] DC = {1, -1, 0, 0};

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode node = root;
            for (char c : w.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) node.children[idx] = new TrieNode();
                node = node.children[idx];
            }
            node.word = w;
        }

        List<String> result = new ArrayList<>();
        int rows = board.length, cols = board[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(board, r, c, root, result);
            }
        }
        return result;
    }

    private void dfs(char[][] board, int r, int c, TrieNode node, List<String> result) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) return;
        char ch = board[r][c];
        if (ch == '#') return;
        TrieNode next = node.children[ch - 'a'];
        if (next == null) return;

        if (next.word != null) {
            result.add(next.word);
            next.word = null; // avoid duplicates
        }

        board[r][c] = '#';
        for (int d = 0; d < 4; d++) dfs(board, r + DR[d], c + DC[d], next, result);
        board[r][c] = ch;
    }

    public static void main(String[] args) {
        WordSearchII sol = new WordSearchII();
        char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        System.out.println(sol.findWords(board, new String[]{"oath","pea","eat","rain"}));
        // [eat, oath]
    }
}
