package stack;

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
