package linked_list;

// LeetCode 287. Find the Duplicate Number
//
// Given array of n+1 integers where each value is in range [1, n].
// Exactly one number is duplicated. Find it.
// Constraints: must not modify the array, O(1) extra space.
//
// Approach: Floyd's Cycle Detection applied to an array
//   Treat the array as a linked list:
//     index i  →  points to  →  nums[i]
//   Since one value is duplicated, two indices point to the same next node,
//   creating a cycle. The ENTRANCE of the cycle = the duplicate number.
//
//   Phase 1 — Find meeting point inside the cycle:
//     slow = nums[slow]         (1 step)
//     fast = nums[nums[fast]]   (2 steps)
//     They meet somewhere inside the cycle.
//
//   Phase 2 — Find cycle entrance (= duplicate):
//     Reset slow to nums[0] (start).
//     Move both one step at a time.
//     Where they meet = entrance = duplicate number.
//
// Example: nums = [1, 3, 4, 2, 2]
//   Index:          0  1  2  3  4
//   0→1→3→2→4→2→4→2... (cycle at 2)
//
// Complexity:
//   Time:  O(n)
//   Space: O(1)

public class FindDuplicateNumber {

    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        // Phase 1: find meeting point inside the cycle
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Phase 2: find cycle entrance = duplicate number
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    public static void main(String[] args) {
        FindDuplicateNumber solution = new FindDuplicateNumber();

        System.out.println(solution.findDuplicate(new int[]{1, 3, 4, 2, 2})); // Expected: 2
        System.out.println(solution.findDuplicate(new int[]{3, 1, 3, 4, 2})); // Expected: 3
        System.out.println(solution.findDuplicate(new int[]{1, 1}));          // Expected: 1
        System.out.println(solution.findDuplicate(new int[]{1, 1, 2}));       // Expected: 1
    }
}
