package stack;

// Problem: For each element in an array, find the nearest element to its LEFT
//          that is strictly smaller. Return -1 if none exists.
// Example: arr = [4, 5, 2, 25, 7, 8]
//          Output: [-1, 4, -1, 2, 2, 7]
//          Explanation: 4→none, 5→4, 2→none, 25→2, 7→2, 8→7
// Approach: Monotonic increasing stack of VALUES (scan left→right).
//   Before pushing arr[i], pop all elements ≥ arr[i] (they can never be the
//   nearest smaller for any future element).
//   Stack top after pops = nearest smaller to the left.
//   Push arr[i] and move on.
// Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;
import java.util.Stack;

public class NearestSmallerToLeft {

    // For each element, find the nearest element to its left that is smaller.
    // Returns -1 if no such element exists.
    public int[] nearestSmallerToLeft(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // Pop elements that are not smaller than current element
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        NearestSmallerToLeft solver = new NearestSmallerToLeft();

        int[] arr = {4, 5, 2, 25, 7, 8};
        int[] result = solver.nearestSmallerToLeft(arr);

        System.out.println("Input:  " + Arrays.toString(arr));
        System.out.println("Output: " + Arrays.toString(result));
        // Expected: [-1, 4, -1, 2, 2, 7]
    }
}
