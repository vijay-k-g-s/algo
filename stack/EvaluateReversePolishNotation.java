package stack;

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


        
        
        
    }
}