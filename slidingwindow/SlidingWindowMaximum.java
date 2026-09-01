package slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Problem (LC 239): Given an integer array and a sliding window of size k that
//          moves from left to right, return the max value in each window position.
// Example: nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
//          Windows:  [1,3,-1]=3  [3,-1,-3]=3  [-1,-3,5]=5  [-3,5,3]=5  [5,3,6]=6  [3,6,7]=7
//          Output:   [3, 3, 5, 5, 6, 7]
// Approach: Monotonic Decreasing Deque (stores indices).
//   - Front of deque = index of max element in current window.
//   - Back of deque  = index of smallest recent element.
//   - Before adding right, pop from back while nums[back] <= nums[right]
//     (smaller elements can never be the max while right is in the window).
//   - Pop from front if the index has fallen outside the window.
// Time: O(n), Space: O(k)

// ─────────────────────────────────────────────────────────────────────────────
// Deque (Double-Ended Queue) — ArrayDeque cheat sheet
//
// Think of it as a line of people where you can add/remove from BOTH ends.
// Front = head (left end),  Back = tail (right end).
//
//  METHOD          ACTION                            EXAMPLE
//  ──────────────────────────────────────────────────────────────────────────
//  offerLast(x)  → add x to the BACK               [1,2] → offerLast(3) → [1,2,3]
//  offerFirst(x) → add x to the FRONT              [1,2] → offerFirst(0) → [0,1,2]
//
//  peekFirst()   → view FRONT without removing      [1,2,3].peekFirst() → 1  (deque unchanged)
//  peekLast()    → view BACK  without removing      [1,2,3].peekLast()  → 3  (deque unchanged)
//
//  pollFirst()   → remove & return FRONT element    [1,2,3] → pollFirst() → returns 1, deque=[2,3]
//  pollLast()    → remove & return BACK  element    [1,2,3] → pollLast()  → returns 3, deque=[1,2]
//
//  isEmpty()     → true if deque has no elements    [].isEmpty() → true
//
// In this problem the deque stores INDICES (not values) so we can check
// whether the max element's index has slid out of the current window.
// ─────────────────────────────────────────────────────────────────────────────

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();  // stores indices, decreasing by value
        int left = 0;
        int ri = 0;                                 // result index

        for (int right = 0; right < n; right++) {
            // remove from back: smaller elements are useless
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[right]) {
                deque.pollLast();
            }
            deque.offerLast(right);                 // expand: add current index

            // remove from front: index out of window
            if (deque.peekFirst() < left) {
                deque.pollFirst();
            }

            if (right - left + 1 == k) {            // window is full
                result[ri++] = nums[deque.peekFirst()];
                left++;                             // slide window
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum solution = new SlidingWindowMaximum();

        System.out.println(Arrays.toString(
            solution.maxSlidingWindow(new int[]{1, 2, 1, 0, 4, 2, 6}, 3)
        )); // Expected: [2, 2, 4, 4, 6]

        System.out.println(Arrays.toString(
            solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)
        )); // Expected: [3, 3, 5, 5, 6, 7]

        System.out.println(Arrays.toString(
            solution.maxSlidingWindow(new int[]{1}, 1)
        )); // Expected: [1]
    }
}
