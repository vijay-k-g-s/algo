package recursion;

public class OnetoN {

    public static void main(String[] args) {

        int n = 5;
         
        helper(n);

    }

    private static void helper(int n) {
        // Base
        if (n == 1) {
            System.out.println(n);
            return;
        }

        // hypotehesis 
        helper(n - 1);

        // Induction
        System.out.println(n);
        
    }
    
}
