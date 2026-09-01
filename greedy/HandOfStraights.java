package greedy;

// Problem (LC 846): Given an integer array hand and groupSize, determine if Alice
//          can rearrange all cards into groups of groupSize consecutive cards.
// Example: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3 → true
//          ([1,2,3],[2,3,4],[6,7,8])
//          hand = [1,2,3,4,5], groupSize = 4 → false
// Approach: Greedy with sorted frequency map (TreeMap).
//   A valid grouping must always start from the smallest unassigned card.
//   Use TreeMap to get cards in sorted order.
//   For each smallest card, try to form a group of `groupSize` consecutive cards.
//   Decrement counts; if any needed count is 0 → impossible.
// Time: O(n log n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.TreeMap;

public class HandOfStraights {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        for (int card : hand) freq.merge(card, 1, Integer::sum);

        while (!freq.isEmpty()) {
            int start = freq.firstKey();
            for (int i = start; i < start + groupSize; i++) {
                if (!freq.containsKey(i)) return false;
                freq.merge(i, -1, Integer::sum);
                if (freq.get(i) == 0) freq.remove(i);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        HandOfStraights sol = new HandOfStraights();
        System.out.println(sol.isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8}, 3)); // true
        System.out.println(sol.isNStraightHand(new int[]{1,2,3,4,5}, 4));          // false
        System.out.println(sol.isNStraightHand(new int[]{1,2,3}, 3));              // true
    }
}
