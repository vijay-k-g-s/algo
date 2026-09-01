package stack;

// Problem (LC 853): n cars are heading to the same destination at position target.
//          Each car has a position and speed. A faster car that catches a slower one
//          becomes a fleet (moves at the slower speed). Return the number of fleets
//          that arrive at the destination.
// Example: target=12, position=[10,8,0,5,3], speed=[2,4,1,1,3]
//          Output: 3
// Approach: Sort cars by starting position (closest to target first).
//   For each car compute time = (target - position) / speed.
//   Use a stack: if the current car's time > stack top, it can't catch up → new fleet.
//   If current time <= top, it merges into the fleet ahead → don't push.
//   Stack size at end = number of fleets.
// Time: O(n log n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;
import java.util.Stack;

public class CarFleet {

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) cars[i] = new int[]{position[i], speed[i]};
        Arrays.sort(cars, (a, b) -> b[0] - a[0]); // sort by position desc (closest to target first)

        Stack<Double> stack = new Stack<>();
        for (int[] car : cars) {
            double time = (double)(target - car[0]) / car[1];
            if (stack.isEmpty() || time > stack.peek()) {
                stack.push(time); // new fleet
            }
            // else: merges into the fleet ahead — don't push
        }
        return stack.size();
    }

    public static void main(String[] args) {
        CarFleet sol = new CarFleet();
        System.out.println(sol.carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3})); // 3
        System.out.println(sol.carFleet(10, new int[]{3}, new int[]{3}));                           // 1
        System.out.println(sol.carFleet(100, new int[]{0, 2, 4}, new int[]{4, 2, 1}));             // 1
    }
}
