package stack;

import java.util.Arrays;
import java.util.Stack;

public class TrappingRainWater {

    // ─────────────────────────────────────────────────────────────────────────
    // Core insight
    // ─────────────────────────────────────────────────────────────────────────
    // For every bar at index i, water it can hold =
    //   min(leftWall[i], rightWall[i]) - height[i]
    //
    // leftWall[i]  = max height seen from index 0 to i   → "Nearest Greater to Left"
    //                (the tallest bar that can hold water on the left side)
    // rightWall[i] = max height seen from index i to n-1 → "Nearest Greater to Right"
    //                (the tallest bar that can hold water on the right side)
    //
    // Three implementations follow, each connecting to NGL / NGR differently.
    // ─────────────────────────────────────────────────────────────────────────

    // ─── Method 1: Explicit NGL / NGR arrays ─────────────────────────────────
    //
    // Precompute:
    //   leftMax[i]  = max(height[0..i])   — acts as Nearest Greater (or equal) to Left
    //   rightMax[i] = max(height[i..n-1]) — acts as Nearest Greater (or equal) to Right
    //
    // Water at i = min(leftMax[i], rightMax[i]) - height[i]
    //
    // Time O(n) | Space O(n)

    public int trapUsingNGL_NGR(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        // --- Nearest Greater to Left (running max from left) ---
        int[] leftMax = new int[n];   // leftMax[i] = effective NGL boundary for bar i
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // --- Nearest Greater to Right (running max from right) ---
        int[] rightMax = new int[n];  // rightMax[i] = effective NGR boundary for bar i
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        System.out.println("height  : " + Arrays.toString(height));
        System.out.println("leftMax : " + Arrays.toString(leftMax));
        System.out.println("rightMax: " + Arrays.toString(rightMax));

        // Water above each bar = min(NGL, NGR) - own height
        int water = 0;
        for (int i = 0; i < n; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return water;
    }

    // ─── Method 2: Stack-based (NGL + NGR discovered on-the-fly) ─────────────
    //
    // Maintain a monotonically non-increasing stack of indices.
    // When height[i] > height[stack.peek()], a valley is detected:
    //
    //   bottom = stack.pop()       ← valley floor (the bar being evaluated)
    //   NGL    = stack.peek()      ← Nearest Greater to Left  of 'bottom'
    //   NGR    = i                 ← Nearest Greater to Right of 'bottom'
    //
    //   water += (min(height[NGL], height[NGR]) - height[bottom]) * (NGR - NGL - 1)
    //
    // Each index is pushed and popped at most once → O(n) time | O(n) space

    public int trapUsingStack(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water = 0;

        for (int i = 0; i < height.length; i++) {
            // height[i] is the NGR for every element in the stack smaller than it
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int bottom = stack.pop();              // valley floor

                if (stack.isEmpty()) break;            // no left wall — skip

                int ngl = stack.peek();                // Nearest Greater to Left
                int ngr = i;                           // Nearest Greater to Right

                int h = Math.min(height[ngl], height[ngr]) - height[bottom];
                int w = ngr - ngl - 1;
                water += h * w;
            }
            stack.push(i);
        }

        return water;
    }

    // ─── Method 3: Two-pointer (O(1) space) ──────────────────────────────────
    //
    // At any point, if height[left] <= height[right] we know:
    //   rightMax >= height[right] >= height[left]
    //   → the limiting wall for 'left' is leftMax (its NGL boundary is already known)
    //   → process left and advance it
    // Otherwise the limiting wall for 'right' is rightMax — process right and advance.
    //
    // Time O(n) | Space O(1)

    public int trapTwoPointer(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;   // running NGL / NGR boundaries
        int water = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) leftMax = height[left];   // update NGL
                else                         water  += leftMax - height[left];
                left++;
            } else {
                if (height[right] >= rightMax) rightMax = height[right]; // update NGR
                else                           water   += rightMax - height[right];
                right--;
            }
        }

        return water;
    }

    // ─────────────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        TrappingRainWater solver = new TrappingRainWater();

        int[][] tests = {
            {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, // expected 6
            {4, 2, 0, 3, 2, 5},                    // expected 9
            {3, 3, 3},                              // expected 0  (flat)
            {5},                                   // expected 0  (single bar)
            {0, 0, 0},                             // expected 0  (all zeros)
        };
        int[] expected = {6, 9, 0, 0, 0};

        for (int t = 0; t < tests.length; t++) {
            int[] h = tests[t];
            System.out.println("=== Test " + (t + 1) + " ===");
            int r1 = solver.trapUsingNGL_NGR(h);
            int r2 = solver.trapUsingStack(h);
            int r3 = solver.trapTwoPointer(h);
            System.out.printf("NGL/NGR arrays : %d%n", r1);
            System.out.printf("Stack (NGL+NGR): %d%n", r2);
            System.out.printf("Two-pointer    : %d%n", r3);
            System.out.printf("Expected       : %d%n%n", expected[t]);
        }
    }
}
