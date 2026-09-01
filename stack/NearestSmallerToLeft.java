package stack;

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
