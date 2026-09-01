package slidingwindow;

import java.util.HashMap;
import java.util.Map;

// Problem (LC 904): You have 2 baskets, each holding only one type of fruit.
//          Starting from any position in an array (each element = a fruit type),
//          pick fruits into your baskets moving right without skipping.
//          Return the maximum number of fruits you can collect.
//          Equivalent to: Longest subarray with at most 2 distinct values.
// Example: fruits = [1, 2, 3, 2, 2] → 4  (subarray [2, 3, 2, 2])
//          fruits = [0, 1, 2, 2]     → 3  (subarray [1, 2, 2])
// Approach: Variable sliding window with a frequency map (basket → count).
//           Shrink from left whenever more than 2 fruit types are in the window.
// Time: O(n), Space: O(1)  (map holds at most 3 entries during shrink)

public class FruitsIntoBaskets {

    public int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0) return 0;

        int left = 0;
        int maxFruits = 0;
        Map<Integer, Integer> basket = new HashMap<>();    // fruit type -> count

        for (int right = 0; right < fruits.length; right++) {
            int fruit = fruits[right];
            basket.put(fruit, basket.getOrDefault(fruit, 0) + 1); // expand window

            while (basket.size() > 2) {                            // shrink until 2 types
                int leftFruit = fruits[left];
                basket.put(leftFruit, basket.get(leftFruit) - 1);
                if (basket.get(leftFruit) == 0) basket.remove(leftFruit);
                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }


    public static void main(String[] args) {
        FruitsIntoBaskets solution = new FruitsIntoBaskets();

        System.out.println(solution.totalFruit(new int[]{1, 2, 1}));          // Expected: 3
        System.out.println(solution.totalFruit(new int[]{0, 1, 2, 2}));       // Expected: 3 ([1,2,2])
        System.out.println(solution.totalFruit(new int[]{1, 2, 3, 2, 2}));    // Expected: 4 ([2,3,2,2])
        System.out.println(solution.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4})); // Expected: 5


    }
}
