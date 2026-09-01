package stack;

import java.util.Arrays;
import java.util.Stack;

public class NearestGreaterToLeft {

    // For each element, find the nearest element to its left that is greater.
    // Returns -1 if no such element exists.
    public int[] nearestGreaterToLeft(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // Pop elements that are not greater than current element
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
            System.out.println(stack);
        }

        return result;
    }

    public static void main(String[] args) {
        NearestGreaterToLeft solver = new NearestGreaterToLeft();

        int[] arr = {4, 5, 2, 25, 7, 8};
        int[] result = solver.nearestGreaterToLeft(arr);

        System.out.println("Input:  " + Arrays.toString(arr));
        System.out.println("Output: " + Arrays.toString(result));
        // Expected: [-1, -1, 5, -1, 25, 25]
    }
}
