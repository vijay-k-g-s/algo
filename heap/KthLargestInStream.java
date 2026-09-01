package heap;

// Problem (LC 703): Design a class that finds the kth largest element in a stream.
//          Constructor takes k and initial array. add(val) adds a value to the
//          stream and returns the kth largest element.
// Example: KthLargest k = new KthLargest(3, [4,5,8,2])
//          k.add(3) → 4   (stream: [2,3,4,5,8], 3rd largest = 4)
//          k.add(5) → 5   (stream: [2,3,4,5,5,8], 3rd largest = 5)
//          k.add(10) → 5
//          k.add(9) → 8
//          k.add(4) → 8
// Approach: Min-Heap of size k.
//   The heap always holds the k largest elements seen so far.
//   Heap top (minimum of the k largest) = kth largest.
//   On add: push val, pop if size > k. Return heap.peek().
// Time: O(log k) per add. Space: O(k)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.PriorityQueue;

public class KthLargestInStream {

    private final PriorityQueue<Integer> minHeap;
    private final int k;

    public KthLargestInStream(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        for (int n : nums) add(n);
    }

    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > k) minHeap.poll();
        return minHeap.peek();
    }

    public static void main(String[] args) {
        KthLargestInStream kl = new KthLargestInStream(3, new int[]{4, 5, 8, 2});
        System.out.println(kl.add(3));  // 4
        System.out.println(kl.add(5));  // 5
        System.out.println(kl.add(10)); // 5
        System.out.println(kl.add(9));  // 8
        System.out.println(kl.add(4));  // 8
    }
}
