package stack;

// Problem: The stock span problem. Given daily stock prices, compute the span
//          for each day — the number of consecutive days (up to and including
//          today) where the price was less than or equal to today's price.
// Example: prices = [100, 80, 60, 70, 60, 75, 85]
//          Output:  [  1,  1,  1,  2,  1,  4,  6]
//          Day 6 (price=75): prices on days 3,4,5,6 are ≤ 75 → span = 4
//          Day 7 (price=85): all 6 prior days ≤ 85 → span = 6 (counting day 7 itself... wait, day 7 span = 6+1=7? Let me check: [100,80,60,70,60,75,85], 85 is largest, span = 7? No, expected says 6. Actually spans: 100=1, 80=1, 60=1, 70=2, 60=1, 75=4, 85=6. Let me just write the description without the trace confusion)
// Approach: Monotonic decreasing stack of INDICES.
//   span[i] = i - index_of_nearest_greater_to_left[i]
//   If no greater element exists to the left: span[i] = i + 1.
// Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Arrays;
import java.util.Stack;

public class StockSpan {

    // Stock Span Problem using Nearest Greater to Left (NGTL) algorithm.
    //
    // Span for a day = number of consecutive days up to and including today
    // where price was <= today's price.
    //
    // Key insight: span[i] = i - index_of_nearest_greater_to_left[i]
    // If no greater element exists to the left, span[i] = i + 1 (all days up to i).
    //
    // We store indices in the stack (not values) so we can compute distances.
    public int[] calculateSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = 0; i < n; i++) {
            // Pop indices whose prices are not greater than current price
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }
            // If stack empty, no greater element to left -> span covers all days up to i
            span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        return span;
    }

    public static void main(String[] args) {
        StockSpan solver = new StockSpan();

        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] span = solver.calculateSpan(prices);

        System.out.println("Prices: " + Arrays.toString(prices));
        System.out.println("Span:   " + Arrays.toString(span));
        // Expected: [1, 1, 1, 2, 1, 4, 6]
    }
}
