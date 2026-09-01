package binarysearch;

public class    SearchA2DMatrix {

    // ─────────────────────────────────────────────────────────────────────────
    // Search a 2D Matrix (LeetCode 74)
    // ─────────────────────────────────────────────────────────────────────────
    // Given an m x n matrix where:
    //   • Each row is sorted left → right
    //   • First element of each row > last element of the previous row
    //
    // The entire matrix is essentially one sorted 1-D array laid out in rows.
    //
    //   Virtual 1-D index  →  matrix cell
    //   mid                →  matrix[mid / cols][mid % cols]
    //
    // ─────────────────────────────────────────────────────────────────────────

    // ─── Method 1: Single Binary Search (treat matrix as 1-D array) ──────────
    //
    // low  = 0
    // high = m*n - 1
    // mid  → row = mid / n, col = mid % n
    //
    // Time O(log(m*n)) | Space O(1)

    public boolean searchSingleBS(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int low  = 0;
        int high = rows * cols - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int row = mid / cols;   // map virtual index → row
            int col = mid % cols;   // map virtual index → col
            int val = matrix[row][col];

            if      (val == target) return true;
            else if (val  < target) low  = mid + 1;  // target in right half
            else                    high = mid - 1;  // target in left half
        }

        return false;
    }

    // ─── Method 2: Two-Step Binary Search ────────────────────────────────────
    //
    // Step 1 — Binary search on the first column to identify the candidate row.
    //           The correct row satisfies: firstElement <= target <= lastElement
    //
    // Step 2 — Binary search within that row.
    //
    // Time O(log m + log n) | Space O(1)
    // (mathematically same as O(log(m*n)), but the two steps are explicit)

    public boolean searchTwoStepBS(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Step 1: find candidate row
        int top = 0, bottom = rows - 1, targetRow = -1;

        while (top <= bottom) {
            int midRow = top + (bottom - top) / 2;

            if (target >= matrix[midRow][0] && target <= matrix[midRow][cols - 1]) {
                targetRow = midRow;  // target lies within this row's range
                break;
            } else if (target < matrix[midRow][0]) {
                bottom = midRow - 1; // target is above this row
            } else {
                top = midRow + 1;    // target is below this row
            }
        }

        if (targetRow == -1) return false; // no candidate row found

        // Step 2: binary search within the candidate row
        int low = 0, high = cols - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int val = matrix[targetRow][mid];

            if      (val == target) return true;
            else if (val  < target) low  = mid + 1;
            else                    high = mid - 1;
        }

        return false;
    }

    // ─────────────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        SearchA2DMatrix solver = new SearchA2DMatrix();

        int[][] matrix = {
            { 1,  3,  5,  7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };

        System.out.println("Matrix:");
        for (int[] row : matrix) {
            for (int val : row) System.out.printf("%4d", val);
            System.out.println();
        }
        System.out.println();

        int[][] tests = {
            {3,  1},   // found,     expected true
            {13, 0},   // not found, expected false
            {1,  1},   // first element
            {60, 1},   // last element
            {23, 1},   // first of last row
        };

        System.out.printf("%-10s %-15s %-15s%n", "Target", "SingleBS", "TwoStepBS");
        System.out.println("─".repeat(42));

        for (int[] test : tests) {
            int target   = test[0];
            boolean exp  = test[1] == 1;
            boolean r1   = solver.searchSingleBS(matrix, target);
            boolean r2   = solver.searchTwoStepBS(matrix, target);
            System.out.printf("%-10d %-15s %-15s   (expected %s)%n",
                    target, r1, r2, exp);
        }
    }
}
