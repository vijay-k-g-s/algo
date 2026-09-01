package twopointers;

// Problem (LC 11): Given an array of heights representing vertical lines, find
//          two lines that together with the x-axis form a container that holds
//          the most water. Return the maximum amount of water.
// Example: height = [1, 8, 6, 2, 5, 4, 8, 3, 7] → 49
//          (lines at index 1 (h=8) and 8 (h=7): width=7, height=7, area=49)
//          height = [1, 1] → 1
// Approach: Two pointers starting at both ends.
//   Area = min(height[left], height[right]) * (right - left).
//   Always move the pointer with the shorter line inward — keeping the taller
//   line gives the best chance of finding a larger container.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxWater = 0;
        while (left < right) {
            int water = Math.min(height[left], height[right]) * (right - left);
            maxWater = Math.max(maxWater, water);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return maxWater;
    }

    public static void main(String[] args) {
        ContainerWithMostWater sol = new ContainerWithMostWater();
        System.out.println(sol.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7})); // 49
        System.out.println(sol.maxArea(new int[]{1, 1}));                       // 1
        System.out.println(sol.maxArea(new int[]{4, 3, 2, 1, 4}));             // 16
    }
}
