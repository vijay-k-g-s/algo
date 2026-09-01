package stack;

import java.util.Arrays;
import java.util.Stack;

public class NearestSmallerToRight {

    // For each element, find the nearest element to its right that is smaller.
    // Returns -1 if no such element exists.
    public int[] nearestSmallerToRight(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
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
        NearestSmallerToRight solver = new NearestSmallerToRight();

        int[] arr = {4, 5, 2, 25, 7, 8};
        int[] result = solver.nearestSmallerToRight(arr);

        System.out.println("Input:  " + Arrays.toString(arr));
        System.out.println("Output: " + Arrays.toString(result));
        // Expected: [2, 2, -1, 7, -1, -1]
    }
}
