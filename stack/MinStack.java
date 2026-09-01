package stack;

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
