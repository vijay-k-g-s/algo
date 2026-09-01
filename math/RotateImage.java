package math;

// Problem (LC 48): Given an n×n 2D matrix, rotate it 90 degrees clockwise IN PLACE.
// Example: [[1,2,3],[4,5,6],[7,8,9]]
//          → [[7,4,1],[8,5,2],[9,6,3]]
// Approach: Two-step in-place rotation.
//   Step 1: Transpose the matrix (swap matrix[i][j] with matrix[j][i]).
//   Step 2: Reverse each row.
//   Transpose + row-reverse = 90° clockwise rotation.
// Time: O(n²), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;

public class RotateImage {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // Step 1: Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // Step 2: Reverse each row
        for (int[] row : matrix) {
            for (int l = 0, r = n - 1; l < r; l++, r--) {
                int tmp = row[l]; row[l] = row[r]; row[r] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        RotateImage sol = new RotateImage();
        int[][] m1 = {{1,2,3},{4,5,6},{7,8,9}};
        sol.rotate(m1);
        for (int[] row : m1) System.out.println(Arrays.toString(row));
        // [7,4,1] [8,5,2] [9,6,3]
    }
}
