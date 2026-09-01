package trie;

// Problem (LC 211): Design a data structure that supports addWord and search.
//   addWord(word)   — adds a word to the structure.
//   search(word)    — returns true if any previously added word matches.
//                     '.' in the word can match any letter.
// Example: addWord("bad"); addWord("dad"); addWord("mad")
//          search("pad") → false; search("bad") → true
//          search(".ad") → true; search("b..") → true
// Approach: Trie with DFS for wildcard '.' matching.
//   For '.' at position i, try all 26 children recursively.
//   For regular char, follow the exact child.
// Time: O(L) per addWord, O(26^L) worst case per search (all dots). Space: O(N*L)
//
// ─────────────────────────────────────────────────────────────────────────────

public class DesignAddAndSearchWords {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private final TrieNode root = new TrieNode();

    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int i, TrieNode node) {
        if (i == word.length()) return node.isEnd;
        char c = word.charAt(i);
        if (c == '.') {
            for (TrieNode child : node.children) {
                if (child != null && dfs(word, i + 1, child)) return true;
            }
            return false;
        }
        TrieNode next = node.children[c - 'a'];
        return next != null && dfs(word, i + 1, next);
    }

    public static void main(String[] args) {
        DesignAddAndSearchWords ws = new DesignAddAndSearchWords();
        ws.addWord("bad"); ws.addWord("dad"); ws.addWord("mad");
        System.out.println(ws.search("pad")); // false
        System.out.println(ws.search("bad")); // true
        System.out.println(ws.search(".ad")); // true
        System.out.println(ws.search("b..")); // true
    }
}
