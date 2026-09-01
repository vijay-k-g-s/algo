package dynamicprogramming;

// Problem (LC 309): Given stock prices, find max profit with the constraint that
//          after selling, you must wait one day before buying again (cooldown).
// Example: prices = [1, 2, 3, 0, 2] → 3  (buy@1, sell@3, cooldown, buy@0, sell@2)
//          prices = [1] → 0
// Approach: State machine DP with 3 states.
//   held:  max profit when holding a stock.
//   sold:  max profit on the day we just sold (must cooldown next day).
//   rest:  max profit in cooldown/rest state (can buy next day).
//   Transitions each day:
//     new_held = max(held, rest - price)   // keep holding or buy from rest
//     new_sold = held + price              // sell today
//     new_rest = max(rest, sold)           // stay resting or come from sold
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class BestTimeToBuyAndSellStockWithCooldown {

    public int maxProfit(int[] prices) {
        int held = Integer.MIN_VALUE, sold = 0, rest = 0;
        for (int price : prices) {
            int prevSold = sold;
            sold = held + price;
            held = Math.max(held, rest - price);
            rest = Math.max(rest, prevSold);
        }
        return Math.max(sold, rest);
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithCooldown sol = new BestTimeToBuyAndSellStockWithCooldown();
        System.out.println(sol.maxProfit(new int[]{1, 2, 3, 0, 2})); // 3
        System.out.println(sol.maxProfit(new int[]{1}));             // 0
        System.out.println(sol.maxProfit(new int[]{2, 1}));          // 0
    }
}
