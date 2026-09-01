package heap;

// Problem (LC 295): Design a data structure that supports:
//   addNum(int num) — adds a number to the data stream.
//   findMedian()    — returns the median of all elements added so far.
// Example: addNum(1); addNum(2); findMedian() → 1.5
//          addNum(3); findMedian() → 2.0
// Approach: Two heaps — maxHeap for lower half, minHeap for upper half.
//   Invariant: maxHeap.size() == minHeap.size() OR maxHeap.size() == minHeap.size() + 1
//   On add: push to maxHeap, balance by moving top of maxHeap to minHeap.
//           If minHeap gets larger, move its top back to maxHeap.
//   findMedian: if sizes equal → average of both tops. Else → maxHeap top.
// Time: O(log n) addNum, O(1) findMedian. Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {

    private final PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // lower half
    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // upper half

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll()); // ensure all in minHeap >= all in maxHeap
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        FindMedianFromDataStream mf = new FindMedianFromDataStream();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian()); // 1.5
        mf.addNum(3);
        System.out.println(mf.findMedian()); // 2.0
        mf.addNum(4);
        System.out.println(mf.findMedian()); // 2.5
    }
}
