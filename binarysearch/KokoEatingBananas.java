package binarysearch;

// Problem (LC 875): Koko loves bananas. There are n piles of bananas. Koko can eat
//          at most k bananas per hour. She wants to eat all bananas in h hours.
//          Return the minimum integer k such that she can finish all piles in h hours.
// Example: piles = [3, 6, 7, 11], h = 8 → 4
//          piles = [30, 11, 23, 4, 20], h = 5 → 30
//          piles = [30, 11, 23, 4, 20], h = 6 → 23
// Approach: Binary search on the answer (k ranges from 1 to max(piles)).
//   For a given k, hours needed = sum of ceil(pile / k) for each pile.
//   If hours <= h, k is feasible → try smaller k (move hi = mid).
//   If hours > h, k too small → move lo = mid + 1.
// Time: O(n log m) where m = max pile. Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int lo = 1, hi = 0;
        for (int p : piles) hi = Math.max(hi, p);

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canFinish(piles, mid, h)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private boolean canFinish(int[] piles, int k, int h) {
        long hours = 0;
        for (int p : piles) hours += (p + k - 1) / k; // ceil(p/k)
        return hours <= h;
    }

    public static void main(String[] args) {
        KokoEatingBananas sol = new KokoEatingBananas();
        System.out.println(sol.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));        // 4
        System.out.println(sol.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));  // 30
        System.out.println(sol.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));  // 23
    }
}
