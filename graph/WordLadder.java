package graph;

// Problem (LC 127): Given a beginWord, endWord, and a wordList, find the length
//          of the shortest transformation sequence from beginWord to endWord.
//          Each step: change exactly one letter; each intermediate word must be in wordList.
//          Return 0 if no such sequence exists.
// Example: beginWord="hit", endWord="cog", wordList=["hot","dot","dog","lot","log","cog"]
//          Output: 5  (hit→hot→dot→dog→cog)
//          beginWord="hit", endWord="cog", wordList=["hot","dot","dog","lot","log"]
//          Output: 0  (cog not in wordList)
// Approach: BFS from beginWord.
//   For each word in the queue, try changing each character to 'a'-'z'.
//   If the new word is in the wordSet, add to queue and remove from set (visited).
//   Return level count when endWord is reached.
// Time: O(n * L * 26) where n = wordList size, L = word length. Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int steps = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) return steps;
                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String next = new String(chars);
                        if (wordSet.contains(next)) {
                            queue.offer(next);
                            wordSet.remove(next);
                        }
                    }
                    chars[j] = original;
                }
            }
            steps++;
        }
        return 0;
    }

    public static void main(String[] args) {
        WordLadder sol = new WordLadder();
        System.out.println(sol.ladderLength("hit", "cog",
            Arrays.asList("hot","dot","dog","lot","log","cog"))); // 5
        System.out.println(sol.ladderLength("hit", "cog",
            Arrays.asList("hot","dot","dog","lot","log")));       // 0
    }
}
