package binarysearch;

// Problem (LC 153): Given a sorted array that has been rotated between 1 and n times,
//          find the minimum element. All elements are unique.
// Example: nums = [3, 4, 5, 1, 2] → 1
//          nums = [4, 5, 6, 7, 0, 1, 2] → 0
//          nums = [11, 13, 15, 17] → 11  (not rotated)
// Approach: Binary search.
//   The minimum is the only element smaller than its predecessor (pivot point).
//   If nums[mid] > nums[right]: minimum is in the right half → lo = mid + 1.
//   Else: minimum is in the left half (including mid) → hi = mid.
//   When lo == hi, we've found the minimum.
// Time: O(log n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) lo = mid + 1;
            else hi = mid;
        }
        return nums[lo];
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray sol = new FindMinimumInRotatedSortedArray();
        System.out.println(sol.findMin(new int[]{3, 4, 5, 1, 2}));       // 1
        System.out.println(sol.findMin(new int[]{4, 5, 6, 7, 0, 1, 2})); // 0
        System.out.println(sol.findMin(new int[]{11, 13, 15, 17}));       // 11
    }
}
