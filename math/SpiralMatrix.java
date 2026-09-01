package math;

// Problem (LC 54): Given an m×n matrix, return all elements in spiral order
//          (right → down → left → up → repeat inward).
// Example: [[1,2,3],[4,5,6],[7,8,9]] → [1,2,3,6,9,8,7,4,5]
//          [[1,2,3,4],[5,6,7,8],[9,10,11,12]] → [1,2,3,4,8,12,11,10,9,5,6,7]
// Approach: Boundary simulation.
//   Maintain top, bottom, left, right boundaries.
//   Traverse: right along top row → down right col → left along bottom row → up left col.
//   Shrink boundaries inward after each traversal.
// Time: O(m*n), Space: O(1) excluding output
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int c = left; c <= right; c++) result.add(matrix[top][c]);
            top++;
            for (int r = top; r <= bottom; r++) result.add(matrix[r][right]);
            right--;
            if (top <= bottom) {
                for (int c = right; c >= left; c--) result.add(matrix[bottom][c]);
                bottom--;
            }
            if (left <= right) {
                for (int r = bottom; r >= top; r--) result.add(matrix[r][left]);
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SpiralMatrix sol = new SpiralMatrix();
        System.out.println(sol.spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        // [1,2,3,6,9,8,7,4,5]
        System.out.println(sol.spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
        // [1,2,3,4,8,12,11,10,9,5,6,7]
    }
}
