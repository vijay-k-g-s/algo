package arrays;

// Problem (LC 271): Design an algorithm to encode a list of strings into a
//          single string, and decode it back to the original list.
//          The encoded string is sent over the network and decoded on the other side.
// Example: ["lint","code","love","you"] → encode → "<some string>" → decode → ["lint","code","love","you"]
//          ["", "hello"] → encode/decode → ["", "hello"]
// Approach: Length-prefix encoding.
//   Encode: for each string s, prepend len(s) + "#" + s.
//     e.g. "lint" → "4#lint", "code" → "4#code"
//     Full encoded: "4#lint4#code4#love3#you"
//   Decode: read digits until '#', parse length, read that many chars as next string.
// Time: O(n) encode, O(n) decode. Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

public class EncodeDecodeStrings {

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append('#').append(s);
        }
        return sb.toString();
    }

    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = s.indexOf('#', i);
            int len = Integer.parseInt(s.substring(i, j));
            result.add(s.substring(j + 1, j + 1 + len));
            i = j + 1 + len;
        }
        return result;
    }

    public static void main(String[] args) {
        EncodeDecodeStrings sol = new EncodeDecodeStrings();

        List<String> input = List.of("lint", "code", "love", "you");
        String encoded = sol.encode(input);
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + sol.decode(encoded)); // [lint, code, love, you]

        List<String> input2 = List.of("", "hello", "world");
        System.out.println("Decoded: " + sol.decode(sol.encode(input2))); // [, hello, world]
    }
}
