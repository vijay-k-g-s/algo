package stack;

// Problem (LC 150): Evaluate an arithmetic expression in Reverse Polish Notation (RPN).
//          Valid operators: +, -, *, /. Each operand and operator is a string token.
//          Division truncates toward zero. The input is always a valid expression.
// Example: tokens = ["2","1","+","3","*"] → 9      ((2+1)*3 = 9)
//          tokens = ["4","13","5","/","+"] → 6      (4 + (13/5) = 4+2 = 6)
//          tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"] → 22
// Approach: Use a stack. For each token:
//   - If operand (not an operator): push Integer value onto stack.
//   - If operator: pop top two values (b = first pop, a = second pop),
//     compute (a op b), push result.
//   Final answer is the single value remaining on the stack.
// Time: O(n), Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.Stack;

class EvaluateReversePolishNotation{

    public int evalRPN(String[] tokens){
        Stack<Integer> st = new Stack<>();

        for(String str : tokens){

            System.out.println(isOperator(str));

            if(isOperator(str)==false){
                System.out.println("Inside If.........");
                int x = Integer.valueOf(str);
                st.push(x);
                System.out.println(st);
            }else{
                int a = st.pop();
                int b = st.pop();
                int c = calculate(b,a,str);
                st.push(c);
            }
        }


        return st.pop();
    }

    public static boolean isOperator(String c){

        return c.equals("+") || c.equals("-") || c.equals("/") || c.equals("*");

    }

    public static int calculate(int a,int b, String c){

        switch(c){
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            default :
                return a/b;        
        }
    }



    public static void main(String[] args) {


        String[] input =  {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        EvaluateReversePolishNotation obj = new EvaluateReversePolishNotation();

        System.out.println(obj.evalRPN(input));

        System.out.println();


        
        
        
    }

}