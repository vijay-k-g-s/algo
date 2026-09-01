package greedy;

// Problem (LC 1899): Given a list of triplets [a,b,c] and target [x,y,z], determine
//          if you can select some triplets and merge them (take element-wise max)
//          to form exactly the target triplet.
// Example: triplets = [[2,5,3],[1,8,4],[1,7,5]], target = [2,7,5]
//          → true  (merge [2,5,3] and [1,7,5] → [2,7,5])
//          triplets = [[3,4,5],[4,5,6]], target = [3,2,5] → false
//          triplets = [[2,5,3],[2,3,4],[1,2,5],[5,2,3]], target = [5,5,5]
//          → true
// Approach: Greedy — only consider "good" triplets (no element exceeds target).
//   A triplet with any element > corresponding target element can NEVER be used
//   (it would make that dimension exceed target).
//   After filtering, take element-wise max of remaining triplets.
//   Check if result equals target.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class MergeTripletsToFormTargetTriplet {

    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int[] merged = new int[3];
        for (int[] t : triplets) {
            if (t[0] > target[0] || t[1] > target[1] || t[2] > target[2]) continue;
            merged[0] = Math.max(merged[0], t[0]);
            merged[1] = Math.max(merged[1], t[1]);
            merged[2] = Math.max(merged[2], t[2]);
        }
        return merged[0] == target[0] && merged[1] == target[1] && merged[2] == target[2];
    }

    public static void main(String[] args) {
        MergeTripletsToFormTargetTriplet sol = new MergeTripletsToFormTargetTriplet();
        System.out.println(sol.mergeTriplets(new int[][]{{2,5,3},{1,8,4},{1,7,5}}, new int[]{2,7,5})); // true
        System.out.println(sol.mergeTriplets(new int[][]{{3,4,5},{4,5,6}}, new int[]{3,2,5}));         // false
        System.out.println(sol.mergeTriplets(new int[][]{{2,5,3},{2,3,4},{1,2,5},{5,2,3}}, new int[]{5,5,5})); // true
    }
}
