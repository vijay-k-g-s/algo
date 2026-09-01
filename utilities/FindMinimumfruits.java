package utilities;

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


