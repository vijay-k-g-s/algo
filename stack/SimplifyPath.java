package stack;

// Problem (LC 71): Given a Unix-style absolute file path, return its simplified
//          (canonical) form. Rules:
//          - "."  means current directory — skip it.
//          - ".." means parent directory  — go up one level (pop).
//          - Multiple slashes "//" → treat as single "/".
//          - Result must start with "/" and not end with "/" (unless root).
// Example: "/a/./b/../../c/"  → "/c"
//          "/home//foo/"      → "/home/foo"
//          "/../"             → "/"   (can't go above root)
//          "/home/user/Documents/../Pictures" → "/home/user/Pictures"
// Approach: Split path by "/", process each component.
//   Use a Deque as a stack of directory names.
//   Skip "" and ".". For ".." pop top if non-empty. Otherwise push the name.
//   Reconstruct by joining deque contents with "/" prefix.
// Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Deque;
import java.util.LinkedList;


public class SimplifyPath {
    public String simplifyPath(String path) {
        // Split the input path by '/'
        String[] components = path.split("/");
        Deque<String> stack = new LinkedList<>();

        // Process each component
        for (String component : components) {
            if (component.equals("") || component.equals(".")) {
                // Skip empty components and '.'
                continue;
            } else if (component.equals("..")) {
                // Handle '..' by popping the last element (if deque is not empty)
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // Push other components onto the stack
                stack.push(component);
            }
        }

        // Construct the simplified path
        StringBuilder simplifiedPath = new StringBuilder();
        while (!stack.isEmpty()) {
            simplifiedPath.append("/").append(stack.pollLast());
        }

        // Handle the case when the path is empty
        return simplifiedPath.length() == 0 ? "/" : simplifiedPath.toString();
    }

    public static void main(String[] args) {
        SimplifyPath solution = new SimplifyPath();
        String path = "/a/./b/../../c/";
        String simplified = solution.simplifyPath(path);
        System.out.println("Simplified Path: " + simplified);
    }
}
