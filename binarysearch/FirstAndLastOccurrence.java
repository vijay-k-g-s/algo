package binarysearch;

import java.util.Arrays;

public class FirstAndLastOccurrence {

    // ─────────────────────────────────────────────────────────────────────────
    // Find First and Last Occurrence (LeetCode 34)
    // ─────────────────────────────────────────────────────────────────────────
    // Given a sorted array (may have duplicates) and a target,
    // return [firstIndex, lastIndex].  Return [-1, -1] if not found.
    //
    // Key insight:
    //   Run binary search TWICE — once biased LEFT (first occurrence)
    //                           — once biased RIGHT (last occurrence)
    //
    //   When arr[mid] == target:
    //     • First occurrence → don't stop, keep going LEFT  (high = mid - 1)
    //     • Last  occurrence → don't stop, keep going RIGHT (low  = mid + 1)
    //
    // Time O(log n) | Space O(1)
    // ─────────────────────────────────────────────────────────────────────────

    // ─── First Occurrence ─────────────────────────────────────────────────────
    //
    // On hitting target at mid, record it but continue searching LEFT
    // to see if an earlier occurrence exists.

    public int firstOccurrence(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                result = mid;       // candidate found
                high = mid - 1;    // keep searching LEFT for an earlier one
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    // ─── Last Occurrence ──────────────────────────────────────────────────────
    //
    // On hitting target at mid, record it but continue searching RIGHT
    // to see if a later occurrence exists.

    public int lastOccurrence(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                result = mid;      // candidate found
                low = mid + 1;    // keep searching RIGHT for a later one
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    // ─── Combined: returns [first, last] ─────────────────────────────────────

    public int[] searchRange(int[] arr, int target) {
        return new int[]{firstOccurrence(arr, target), lastOccurrence(arr, target)};
    }

    // ─────────────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        FirstAndLastOccurrence solver = new FirstAndLastOccurrence();

        System.out.println("=== First & Last Occurrence ===\n");

        // target appears multiple times
        int[] arr1 = {1, 2, 2, 2, 3, 4, 5};
        System.out.println("Array : " + Arrays.toString(arr1));
        System.out.println("Search 2 → " + Arrays.toString(solver.searchRange(arr1, 2))
                + "  (expected [1, 3])");

        // target appears once
        int[] arr2 = {1, 3, 5, 7, 9};
        System.out.println("\nArray : " + Arrays.toString(arr2));
        System.out.println("Search 5 → " + Arrays.toString(solver.searchRange(arr2, 5))
                + "  (expected [2, 2])");

        // target not found
        System.out.println("Search 6 → " + Arrays.toString(solver.searchRange(arr2, 6))
                + "  (expected [-1, -1])");

        // target at boundaries
        int[] arr3 = {2, 2, 2, 2, 2};
        System.out.println("\nArray : " + Arrays.toString(arr3));
        System.out.println("Search 2 → " + Arrays.toString(solver.searchRange(arr3, 2))
                + "  (expected [0, 4])");

        // single element
        int[] arr4 = {5};
        System.out.println("\nArray : " + Arrays.toString(arr4));
        System.out.println("Search 5 → " + Arrays.toString(solver.searchRange(arr4, 5))
                + "  (expected [0, 0])");
        System.out.println("Search 3 → " + Arrays.toString(solver.searchRange(arr4, 3))
                + "  (expected [-1, -1])");
    }
}
