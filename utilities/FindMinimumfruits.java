package utilities;

// Utility / Scratch: Toggle-set technique — track elements that appear an odd
//   number of times using a HashSet.
//   If element is already in set → remove it (second occurrence cancels first).
//   If element is not in set   → add it (first / odd occurrence).
//   After processing, set contains only elements with an odd count.
// Example: arr = [3, 3, 3, 1]
//          After 3 (odd count): set = {3}
//          After 3 again (even): set = {}... after third 3: set = {3}
//          After 1: set = {3, 1}
// Note: This is an exploratory scratch — not a complete solution.
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.HashSet;
import java.util.Set;

public class FindMinimumfruits {

    public static int findMinimumFruits(int[] arr){

        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < arr.length; i++){
            
            if(set.contains(arr[i])){
                set.remove(arr[i]);
            }else{
                set.add(arr[i]);
            }
        }
        System.out.println(set);
        return 0;

    }


    
    public static void main(String[] args) {

        int[] arr = {3,3,3,1};

        FindMinimumfruits obj = new FindMinimumfruits();
        obj.findMinimumFruits(arr);

        

    }

    
}


