package stack;

// Problem (LC 155): Design a stack that supports push, pop, top, and retrieving
//          the minimum element — all in O(1) time.
// Example: MinStack ms = new MinStack();
//          ms.push(-2); ms.push(0); ms.push(-3);
//          ms.getMin() → -3
//          ms.pop();
//          ms.top()    → 0
//          ms.getMin() → -2
// Approach: Maintain two stacks — a main stack and a parallel minStack.
//   minStack[i] stores the running minimum at the time element i was pushed.
//   On push(x): minSt.push(min(current_min, x)).
//   On pop(): pop both stacks together so minSt always stays in sync.
//   getMin() peeks minSt top — always O(1).
// Time: O(1) all operations, Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Stack;

public class MinStack {

    private Stack<Integer> st;
    private Stack<Integer> minSt;
    private int min;

    MinStack(){
        st = new Stack<>();
        minSt = new Stack<>();
        min = Integer.MAX_VALUE;
        minSt.push(min);
    }

    public void push(int x){
        st.push(x);
        min = Math.min(min,x);
        minSt.push(min);

    }

    public void pop(){
        st.pop();
        min = minSt.peek();
        minSt.pop();
    }

    public int top(){
        return st.peek();
    }

    public int getMin(){
        return min;
    }

    public static void main(String[] args){

        MinStack ms = new MinStack();
        ms.push(10);
        ms.pop();

        System.out.println(ms.getMin());

    }


}
