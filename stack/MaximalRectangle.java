package stack;

// Problem (LC 85): Given a binary matrix of '0's and '1's, find the area of
//          the largest rectangle containing only '1's.
// Example: matrix = [["1","0","1","0","0"],
//                    ["1","0","1","1","1"],
//                    ["1","1","1","1","1"],
//                    ["1","0","0","1","0"]]
//          Output: 6  (3 columns × 2 rows in the middle-right region)
//          matrix = [["0"]] → 0,  matrix = [["1"]] → 1
// Approach: Reduce to LargestRectangleInHistogram row by row.
//   heights[j] = consecutive 1's ending at matrix[i][j] in column j.
//   If matrix[i][j] == '0': heights[j] = 0 (break in column).
//   If matrix[i][j] == '1': heights[j] += 1 (extend column streak).
//   Run NSL + NSR histogram algorithm on each row's heights array.
//   Answer = max area seen across all rows.
// Time: O(rows × cols), Space: O(cols)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Stack;

public class MaximalRectangle {

    // Strategy: treat each row as the base of a histogram.
    //
    // heights[j] = number of consecutive 1's directly above (and including)
    //              matrix[i][j] in column j.
    //
    // For each row i, update heights[], then run the
    // LargestRectangleInHistogram algorithm (NSL + NSR approach) on it.
    // The answer is the maximum area seen across all rows.
    //
    // Time:  O(rows * cols)
    // Space: O(cols)

    private int[] nearestSmallerToLeft(int[] heights) {
        int n = heights.length;
        int[] nsl = new int[n];
        Stack<Integer> stack = new Stack<>();

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
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            nsr[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return nsr;
    }

    private int largestRectangleInHistogram(int[] heights) {
        int[] nsl = nearestSmallerToLeft(heights);
        int[] nsr = nearestSmallerToRight(heights);

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int width = nsr[i] - nsl[i] - 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }
        return maxArea;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;

        for (int i = 0; i < rows; i++) {
            // Build histogram heights for this row
            for (int j = 0; j < cols; j++) {
                heights[j] = (matrix[i][j] == '1') ? heights[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, largestRectangleInHistogram(heights));
        }

        return maxArea;
    }

    public static void main(String[] args) {
        MaximalRectangle solver = new MaximalRectangle();

        // Example 1 — expected 6
        char[][] matrix1 = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        System.out.println("Matrix 1 — expected 6, got: " + solver.maximalRectangle(matrix1));

        // Example 2 — single '0', expected 0
        char[][] matrix2 = {{'0'}};
        System.out.println("Matrix 2 — expected 0, got: " + solver.maximalRectangle(matrix2));

        // Example 3 — single '1', expected 1
        char[][] matrix3 = {{'1'}};
        System.out.println("Matrix 3 — expected 1, got: " + solver.maximalRectangle(matrix3));

        // Example 4 — all 1s 2x3, expected 6
        char[][] matrix4 = {
            {'1','1','1'},
            {'1','1','1'}
        };
        System.out.println("Matrix 4 — expected 6, got: " + solver.maximalRectangle(matrix4));
    }
}
