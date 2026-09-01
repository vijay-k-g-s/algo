package stack;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    // "Next Greater Element to the Right" — but return the INDEX GAP, not the value.
    //
    // Strategy: monotonically decreasing stack of INDICES (not values).
    //
    //   Scan left → right.
    //   When temps[i] > temps[stack.peek()]:
    //     • the top index finally has a warmer future day → result[top] = i - top
    //     • pop and repeat (handles multiple pending indices at once)
    //   Push i unconditionally.
    //
    //   Indices still in the stack when the loop ends have no warmer day → result stays 0.
    //
    // Why indices instead of values?
    //   NearestGreaterToRight stores values and scans right → left.
    //   Here we need the distance, so we store indices and scan left → right,
    //   resolving each "waiting" day the moment its answer is known.
    //
    // Time:  O(n)  — each index pushed and popped at most once
    // Space: O(n)  — stack

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n]; // default 0 — covers "no future warmer day"
        Stack<Integer> stack = new Stack<>(); // stores indices, non-increasing by temperature

        for (int i = 0; i < n; i++) {
            // Pop every waiting index whose warmer day has arrived
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int waitingIdx = stack.pop();
                result[waitingIdx] = i - waitingIdx;
            }
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        DailyTemperatures solver = new DailyTemperatures();

        // Example 1 — expected [1, 1, 4, 2, 1, 1, 0, 0]
        int[] t1 = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("Input:    " + Arrays.toString(t1));
        System.out.println("Expected: [1, 1, 4, 2, 1, 1, 0, 0]");
        System.out.println("Got:      " + Arrays.toString(solver.dailyTemperatures(t1)));

        System.out.println();

        // Example 2 — expected [1, 1, 1, 0]
        int[] t2 = {30, 40, 50, 60};
        System.out.println("Input:    " + Arrays.toString(t2));
        System.out.println("Expected: [1, 1, 1, 0]");
        System.out.println("Got:      " + Arrays.toString(solver.dailyTemperatures(t2)));

        System.out.println();

        // Example 3 — strictly decreasing, expected [0, 0, 0, 0]
        int[] t3 = {30, 20, 10, 5};
        System.out.println("Input:    " + Arrays.toString(t3));
        System.out.println("Expected: [0, 0, 0, 0]");
        System.out.println("Got:      " + Arrays.toString(solver.dailyTemperatures(t3)));

        System.out.println();

        // Edge: single element — expected [0]
        int[] t4 = {55};
        System.out.println("Input:    " + Arrays.toString(t4));
        System.out.println("Expected: [0]");
        System.out.println("Got:      " + Arrays.toString(solver.dailyTemperatures(t4)));
    }
}
