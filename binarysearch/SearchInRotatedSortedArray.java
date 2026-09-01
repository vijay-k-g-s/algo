package binarysearch;

public class    SearchInRotatedSortedArray {

    // ─────────────────────────────────────────────────────────────────────────
    // Search in Rotated Sorted Array (LeetCode 33)
    // ─────────────────────────────────────────────────────────────────────────
    // A sorted array is rotated at some unknown pivot.
    // e.g. [0,1,2,4,5,6,7]  rotated at index 3  →  [4,5,6,7,0,1,2]
    //
    // Key insight:
    //   After splitting at mid, AT LEAST ONE half is always fully sorted.
    //   Determine which half is sorted, then check if target lies in it.
    //
    //   Case 1: Left half [low..mid] is sorted  →  arr[low] <= arr[mid]
    //     • target in [arr[low], arr[mid])  →  go left  (high = mid - 1)
    //     • otherwise                       →  go right (low  = mid + 1)
    //
    //   Case 2: Right half [mid..high] is sorted  →  arr[mid] <= arr[high]
    //     • target in (arr[mid], arr[high]]  →  go right (low  = mid + 1)
    //     • otherwise                        →  go left  (high = mid - 1)
    //
    // Time O(log n) | Space O(1)
    // ─────────────────────────────────────────────────────────────────────────

    // ─── No duplicates (LeetCode 33) ─────────────────────────────────────────

    public int search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) return mid;

            // Left half [low..mid] is sorted
            if (arr[low] <= arr[mid]) {
                if (target >= arr[low] && target < arr[mid]) {
                    high = mid - 1;  // target lies in sorted left half
                } else {
                    low = mid + 1;   // target is in right half
                }
            }
            // Right half [mid..high] is sorted
            else {
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;   // target lies in sorted right half
                } else {
                    high = mid - 1;  // target is in left half
                }
            }
        }

        return -1;
    }

    // ─── With duplicates (LeetCode 81) ───────────────────────────────────────
    //
    // When arr[low] == arr[mid] == arr[high], we cannot determine which half
    // is sorted (e.g. [3,1,3,3,3]).
    // Solution: shrink both ends by 1 and retry.
    //
    // Worst-case Time O(n) | Average O(log n) | Space O(1)

    public boolean searchWithDuplicates(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) return true;

            // Cannot determine sorted half — shrink both ends
            if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
                low++;
                high--;
                continue;
            }

            // Left half [low..mid] is sorted
            if (arr[low] <= arr[mid]) {
                if (target >= arr[low] && target < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // Right half [mid..high] is sorted
            else {
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return false;
    }

    // ─────────────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        SearchInRotatedSortedArray solver = new SearchInRotatedSortedArray();

        System.out.println("=== No Duplicates (LeetCode 33) ===");
        int[] arr1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Array: [4, 5, 6, 7, 0, 1, 2]");
        System.out.println();

        // target in left sorted half
        System.out.println("Search 5  → " + solver.search(arr1, 5)  + "  (expected  1)");
        // target in right sorted half
        System.out.println("Search 0  → " + solver.search(arr1, 0)  + "  (expected  4)");
        // target at pivot boundary
        System.out.println("Search 4  → " + solver.search(arr1, 4)  + "  (expected  0)");
        // target not present
        System.out.println("Search 3  → " + solver.search(arr1, 3)  + "  (expected -1)");
        // single element
        int[] arr2 = {1};
        System.out.println("Search 0  in [1] → " + solver.search(arr2, 0) + "  (expected -1)");
        System.out.println("Search 1  in [1] → " + solver.search(arr2, 1) + "  (expected  0)");

        System.out.println();
        System.out.println("=== With Duplicates (LeetCode 81) ===");
        int[] arr3 = {2, 5, 6, 0, 0, 1, 2};
        System.out.println("Array: [2, 5, 6, 0, 0, 1, 2]");
        System.out.println();

        System.out.println("Search 0  → " + solver.searchWithDuplicates(arr3, 0) + "  (expected true)");
        System.out.println("Search 3  → " + solver.searchWithDuplicates(arr3, 3) + "  (expected false)");

        int[] arr4 = {3, 1, 3, 3, 3};
        System.out.println("Search 1  in [3,1,3,3,3] → " + solver.searchWithDuplicates(arr4, 1) + "  (expected true)");
        System.out.println("Search 2  in [3,1,3,3,3] → " + solver.searchWithDuplicates(arr4, 2) + "  (expected false)");
    }
}
