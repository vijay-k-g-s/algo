package stack;

import java.util.Stack;

public class LargestRectangleInHistogram {

    // Largest Rectangle in Histogram using Nearest Smaller to Left (NSL)
    // and Nearest Smaller to Right (NSR).
    //
    // For each bar i, the largest rectangle with heights[i] as the shortest bar spans:
    //   left  boundary = NSL[i] (index of nearest smaller to left)
    //   right boundary = NSR[i] (index of nearest smaller to right)
    //   width = NSR[i] - NSL[i] - 1
    //   area  = heights[i] * width
    //
    // Answer = max area across all bars.

    private int[] nearestSmallerToLeft(int[] heights) {
        int n = heights.length;
        int[] nsl = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            nsl[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return nsl;
    }

    private int[] nearestSmallerToRight(int[] heights) {
        int n = heights.length;
        int[] nsr = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            nsr[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return nsr;
    }

    public int largestRectangle(int[] heights) {
        int n = heights.length;
        int[] nsl = nearestSmallerToLeft(heights);
        int[] nsr = nearestSmallerToRight(heights);

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = nsr[i] - nsl[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram solver = new LargestRectangleInHistogram();

        int[] heights1 = {2, 1, 5, 6, 2, 3};
        System.out.println("Heights: [2, 1, 5, 6, 2, 3]");
        System.out.println("Largest Rectangle: " + solver.largestRectangle(heights1));
        // Expected: 10  (bars 5 and 6, width=2)

        int[] heights2 = {2, 4};
        System.out.println("\nHeights: [2, 4]");
        System.out.println("Largest Rectangle: " + solver.largestRectangle(heights2));
        // Expected: 4
    }
}
