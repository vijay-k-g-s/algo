package binarysearch;

import java.util.Arrays;

public class CountOccurrences {

    // ─────────────────────────────────────────────────────────────────────────
    // Count Occurrences of an Element in a Sorted Array
    // ─────────────────────────────────────────────────────────────────────────
    // Given a sorted array (may have duplicates) and a target,
    // return how many times target appears.
    //
    // Key insight:
    //   count = lastOccurrence - firstOccurrence + 1
    //
    //   Reuse the two biased binary searches:
    //     • firstOccurrence → bias LEFT  (high = mid - 1 on match)
    //     • lastOccurrence  → bias RIGHT (low  = mid + 1 on match)
    //
    // Time O(log n) | Space O(1)
    // ─────────────────────────────────────────────────────────────────────────

    // ─── First Occurrence ────────────────────────────────────────────────────

    private int firstOccurrence(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                result = mid;      // candidate found
                high = mid - 1;   // bias LEFT — look for an earlier occurrence
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    // ─── Last Occurrence ─────────────────────────────────────────────────────

    private int lastOccurrence(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                result = mid;     // candidate found
                low = mid + 1;   // bias RIGHT — look for a later occurrence
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    // ─── Count ───────────────────────────────────────────────────────────────
    //
    //  Array:  [1, 2, 2, 2, 3, 4, 5]
    //                ↑        ↑
    //           first=1    last=3
    //
    //  count = last - first + 1 = 3 - 1 + 1 = 3

    public int count(int[] arr, int target) {
        int first = firstOccurrence(arr, target);

        if (first == -1) return 0;   // target not present at all

        int last = lastOccurrence(arr, target);

        return last - first + 1;
    }

    // ─────────────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        CountOccurrences solver = new CountOccurrences();

        System.out.println("=== Count Occurrences in a Sorted Array ===\n");

        int[] arr1 = {1, 2, 2, 2, 3, 4, 5};
        System.out.println("Array : " + Arrays.toString(arr1));
        System.out.println("Count 2 → " + solver.count(arr1, 2) + "  (expected 3)");
        System.out.println("Count 1 → " + solver.count(arr1, 1) + "  (expected 1)");
        System.out.println("Count 6 → " + solver.count(arr1, 6) + "  (expected 0)");

        int[] arr2 = {2, 2, 2, 2, 2};
        System.out.println("\nArray : " + Arrays.toString(arr2));
        System.out.println("Count 2 → " + solver.count(arr2, 2) + "  (expected 5)");

        int[] arr3 = {1, 3, 5, 7, 9};
        System.out.println("\nArray : " + Arrays.toString(arr3));
        System.out.println("Count 5 → " + solver.count(arr3, 5) + "  (expected 1)");
        System.out.println("Count 4 → " + solver.count(arr3, 4) + "  (expected 0)");

        int[] arr4 = {5};
        System.out.println("\nArray : " + Arrays.toString(arr4));
        System.out.println("Count 5 → " + solver.count(arr4, 5) + "  (expected 1)");
        System.out.println("Count 3 → " + solver.count(arr4, 3) + "  (expected 0)");
    }
}
