package slidingwindow;

// Problem: Given an integer array and a number k, find the maximum sum of any
//          contiguous subarray of exactly size k.
// Example: arr = [2, 1, 5, 1, 3, 2], k = 3 → 9  (subarray [5, 1, 3])
// Approach: Fixed sliding window — maintain a running sum; when window reaches
//           size k, record the max, subtract the leftmost element, and slide.
// Time: O(n), Space: O(1)

public class MaxSumSubarrayOfSizeK {

    public int maxSum(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            throw new IllegalArgumentException("Array length must be >= k");
        }

        int left = 0;
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int right = 0; right < arr.length; right++) {
            windowSum += arr[right];                        // expand window

            if (right - left + 1 == k) {                   // window is full
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[left];                     // shrink window
                left++;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaxSumSubarrayOfSizeK solution = new MaxSumSubarrayOfSizeK();

        int[] arr1 = {2, 1, 5, 1, 3, 2};
        int k1 = 3;
        System.out.println("Max sum (k=3): " + solution.maxSum(arr1, k1)); // Expected: 9

        int[] arr2 = {2, 3, 4, 1, 5};
        int k2 = 2;
        System.out.println("Max sum (k=2): " + solution.maxSum(arr2, k2)); // Expected: 7
    }
}
