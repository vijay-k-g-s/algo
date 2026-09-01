package slidingwindow;

// Problem (LC 121): Given an array where prices[i] is the stock price on day i,
//          find the maximum profit by buying on one day and selling on a later day.
//          Return 0 if no profit is possible.
// Example: prices = [7, 1, 5, 3, 6, 4] → 5  (buy at 1, sell at 6)
//          prices = [7, 6, 4, 3, 1]     → 0  (prices only decrease)
// Approach: Sliding window / single pass.
//   Track the minimum price seen so far (buy day).
//   For each day, compute profit = price - minPrice; update maxProfit.
//   Move minPrice forward whenever a lower price is found.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock sol = new BestTimeToBuyAndSellStock();
        System.out.println(sol.maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // 5
        System.out.println(sol.maxProfit(new int[]{7, 6, 4, 3, 1}));    // 0
        System.out.println(sol.maxProfit(new int[]{2, 4, 1}));           // 2
    }
}
