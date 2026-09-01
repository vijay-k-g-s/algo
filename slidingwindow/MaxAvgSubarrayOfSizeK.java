package slidingwindow;

// Problem (LC 643): Given an integer array and integer k, find the contiguous
//          subarray of length k with the maximum average value and return that average.
// Example: arr = [1, 12, -5, -6, 50, 3], k = 4 → 12.75  (subarray [12, -5, -6, 50] = 51/4)
// Approach: Fixed sliding window — track the running sum; divide by k each time
//           the window is full to compute the average.
// Time: O(n), Space: O(1)

public class MaxAvgSubarrayOfSizeK {

    public double maxAvg(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            throw new IllegalArgumentException("Array length must be >= k");
        }

        int left = 0;
        int windowSum = 0;
        double maxAvg = Double.MIN_VALUE;

        for (int right = 0; right < arr.length; right++) {
            windowSum += arr[right];                        // expand window

            if (right - left + 1 == k) {                   // window is full
                maxAvg = Math.max(maxAvg, (double) windowSum / k);
                windowSum -= arr[left];                     // shrink window
                left++;
            }
        }

        return maxAvg;
    }

    public static void main(String[] args) {
        MaxAvgSubarrayOfSizeK solution = new MaxAvgSubarrayOfSizeK();

        int[] arr1 = {1, 12, -5, -6, 50, 3};
        int k1 = 4;
        System.out.println("Max avg (k=4): " + solution.maxAvg(arr1, k1)); // Expected: 12.75

        int[] arr2 = {5, 5, 5, 5, 5};
        int k2 = 2;
        System.out.println("Max avg (k=2): " + solution.maxAvg(arr2, k2)); // Expected: 5.0
    }
}
