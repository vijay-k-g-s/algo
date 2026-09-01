
package utilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordConcatenations {

    public List<String> getConcatenations(String[] words) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }

        // Generate all permutations of the given words
        List<List<String>> permutations = new ArrayList<>();
        permute(words, 0, permutations);

        // Concatenate each permutation into a single string
        for (List<String> permutation : permutations) {
            StringBuilder sb = new StringBuilder();
            for (String word : permutation) {
                sb.append(word);
            }
            result.add(sb.toString());
        }

        return result;
    }

    private void permute(String[] words, int start, List<List<String>> result) {
        if (start == words.length) {
            List<String> permutation = new ArrayList<>();
            Collections.addAll(permutation, words);
            result.add(permutation);
        } else {
            for (int i = start; i < words.length; i++) {
                swap(words, start, i);
                permute(words, start + 1, result);
                swap(words, start, i);
            }
        }
    }

    private void swap(String[] words, int i, int j) {
        String temp = words[i];
        words[i] = words[j];
        words[j] = temp;
    }

    public static void main(String[] args) {
        WordConcatenations solution = new WordConcatenations();
        String[] words = {"foo", "bar"};
        List<String> concatenations = solution.getConcatenations(words);
        System.out.println("Concatenated words: " + concatenations);
    }
}
