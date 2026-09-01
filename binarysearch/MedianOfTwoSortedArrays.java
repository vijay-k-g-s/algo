package binarysearch;

// Problem (LC 4): Given two sorted arrays nums1 and nums2, return the median of
//          the two sorted arrays. The overall run time must be O(log(m+n)).
// Example: nums1 = [1, 3], nums2 = [2]     → 2.0
//          nums1 = [1, 2], nums2 = [3, 4]  → 2.5
// Approach: Binary search on the smaller array.
//   Partition both arrays so left halves together have (m+n+1)/2 elements.
//   Binary search on partition point i in nums1; j = half - i in nums2.
//   Condition for correct partition:
//     nums1[i-1] <= nums2[j] AND nums2[j-1] <= nums1[i]
//   If not, adjust i. Median = max of left halves (odd total)
//   or average of max-left and min-right (even total).
// Time: O(log(min(m,n))), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        int m = nums1.length, n = nums2.length;
        int lo = 0, hi = m;
        int half = (m + n + 1) / 2;

        while (lo <= hi) {
            int i = lo + (hi - lo) / 2;
            int j = half - i;

            int maxLeft1  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRight1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int maxLeft2  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRight2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((m + n) % 2 == 1) return Math.max(maxLeft1, maxLeft2);
                return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
            } else if (maxLeft1 > minRight2) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays sol = new MedianOfTwoSortedArrays();
        System.out.println(sol.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));    // 2.0
        System.out.println(sol.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4})); // 2.5
        System.out.println(sol.findMedianSortedArrays(new int[]{}, new int[]{1}));        // 1.0
    }
}
