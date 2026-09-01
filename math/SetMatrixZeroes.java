package math;

// Problem (LC 73): Given an m×n matrix, if an element is 0, set its entire row
//          and column to 0. Do it IN PLACE.
// Example: [[1,1,1],[1,0,1],[1,1,1]] → [[1,0,1],[0,0,0],[1,0,1]]
//          [[0,1,2,0],[3,4,5,2],[1,3,1,5]] → [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
// Approach: Use first row and first column as markers.
//   1. Check if row 0 or col 0 originally contains a 0 (track with flags).
//   2. For each cell (i>0, j>0): if matrix[i][j]==0, mark matrix[i][0] and matrix[0][j]=0.
//   3. Zero out cells based on markers in row 0 and col 0.
//   4. Zero out row 0 and col 0 if originally they had a 0.
// Time: O(m*n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;

public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean row0 = false, col0 = false;

        for (int j = 0; j < n; j++) if (matrix[0][j] == 0) row0 = true;
        for (int i = 0; i < m; i++) if (matrix[i][0] == 0) col0 = true;

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][j] == 0) { matrix[i][0] = 0; matrix[0][j] = 0; }

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;

        if (row0) Arrays.fill(matrix[0], 0);
        if (col0) for (int i = 0; i < m; i++) matrix[i][0] = 0;
    }

    public static void main(String[] args) {
        SetMatrixZeroes sol = new SetMatrixZeroes();
        int[][] m = {{1,1,1},{1,0,1},{1,1,1}};
        sol.setZeroes(m);
        for (int[] row : m) System.out.println(Arrays.toString(row));
        // [1,0,1] [0,0,0] [1,0,1]
    }
}
