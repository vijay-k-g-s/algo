package binarysearch;

public class BinarySearch {

    // ─────────────────────────────────────────────────────────────────────────
    // Binary Search
    // ─────────────────────────────────────────────────────────────────────────
    // Given a sorted array and a target, return the index of the target.
    // Returns -1 if the target is not found.
    //
    // Invariant: target, if present, is always within arr[low..high]
    //
    // Time  O(log n) | Space O(1) iterative, O(log n) recursive (call stack)
    // ─────────────────────────────────────────────────────────────────────────

    // ─── Iterative ────────────────────────────────────────────────────────────

    public int searchIterative(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;   // avoids integer overflow

            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low  = mid + 1;  // target is in right half
            else                        high = mid - 1;  // target is in left half
        }

        return -1; // not found
    }

    // ─── Recursive ────────────────────────────────────────────────────────────

    public int searchRecursive(int[] arr, int target) {
        return helper(arr, target, 0, arr.length - 1);
    }

    private int helper(int[] arr, int target, int low, int high) {
        if (low > high) return -1;  // base case: search space exhausted

        int mid = low + (high - low) / 2;

        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) return helper(arr, target, mid + 1, high); // right half
        else                        return helper(arr, target, low, mid - 1);  // left half
    }

    // ─────────────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};

        System.out.println("Array: [1, 3, 5, 7, 9, 11, 13, 15]");
        System.out.println();

        // target found
        System.out.println("Search 7  → iterative: " + bs.searchIterative(arr, 7)  + " (expected 3)");
        System.out.println("Search 7  → recursive: " + bs.searchRecursive(arr, 7)  + " (expected 3)");

        // first element
        System.out.println("Search 1  → iterative: " + bs.searchIterative(arr, 1)  + " (expected 0)");
        System.out.println("Search 1  → recursive: " + bs.searchRecursive(arr, 1)  + " (expected 0)");

        // last element
        System.out.println("Search 15 → iterative: " + bs.searchIterative(arr, 15) + " (expected 7)");
        System.out.println("Search 15 → recursive: " + bs.searchRecursive(arr, 15) + " (expected 7)");

        // not found
        System.out.println("Search 6  → iterative: " + bs.searchIterative(arr, 6)  + " (expected -1)");
        System.out.println("Search 6  → recursive: " + bs.searchRecursive(arr, 6)  + " (expected -1)");
    }
}
