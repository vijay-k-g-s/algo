package math;

// Problem (LC 202): A happy number is defined by: starting with any positive integer,
//          replace it with the sum of squares of its digits, and repeat until it equals 1
//          (happy) or loops endlessly (not happy).
// Example: 19 → 1²+9²=82 → 8²+2²=68 → 6²+8²=100 → 1²+0²+0²=1 → true
//          2 → ... → eventually cycles back → false
// Approach: Floyd's cycle detection (fast and slow pointers).
//   slow = next(n), fast = next(next(n)).
//   If fast reaches 1 → happy. If fast == slow → cycle → not happy.
// Time: O(log n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class HappyNumber {

    public boolean isHappy(int n) {
        int slow = n, fast = getNext(n);
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        HappyNumber sol = new HappyNumber();
        System.out.println(sol.isHappy(19)); // true
        System.out.println(sol.isHappy(2));  // false
        System.out.println(sol.isHappy(1));  // true
    }
}
