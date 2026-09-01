package stack;

import java.util.Arrays;
import java.util.Stack;

public class NearestGreaterToRight {

    // For each element, find the nearest element to its right that is greater.
    // Returns -1 if no such element exists.
    public int[] nearestGreaterToRight(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            // Pop elements that are not greater than current element
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        NearestGreaterToRight solver = new NearestGreaterToRight();

        int[] arr = {4, 5, 2, 25, 7, 8};
        int[] result = solver.nearestGreaterToRight(arr);

        System.out.println("Input:  " + Arrays.toString(arr));
        System.out.println("Output: " + Arrays.toString(result));
        // Expected: [5, 25, 25, -1, 8, -1]
    }
}
