package trie;

// Problem (LC 208): Implement a Trie (Prefix Tree) with insert, search, and startsWith.
//   insert(word)           — inserts word into the trie.
//   search(word)           — returns true if word is in the trie (exact match).
//   startsWith(prefix)     — returns true if any word in the trie starts with prefix.
// Example: insert("apple"); search("apple") → true; search("app") → false
//          startsWith("app") → true; insert("app"); search("app") → true
// Approach: Each TrieNode has an array of 26 child pointers (one per lowercase letter)
//   and an isEnd flag. Traverse character by character, creating nodes as needed.
// Time: O(L) per operation where L = word length. Space: O(ALPHABET * N * L)
//
// ─────────────────────────────────────────────────────────────────────────────

public class ImplementTrie {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = traverse(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return traverse(prefix) != null;
    }

    private TrieNode traverse(String s) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) return null;
            node = node.children[idx];
        }
        return node;
    }

    public static void main(String[] args) {
        ImplementTrie trie = new ImplementTrie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));      // true
        System.out.println(trie.search("app"));        // false
        System.out.println(trie.startsWith("app"));    // true
        trie.insert("app");
        System.out.println(trie.search("app"));        // true
    }
}
