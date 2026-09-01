package utilities;

import java.util.ArrayList;

public class ArrayListToArray {


    public static void main(String[] args) {

        // Create an ArrayList of Integer
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        // Convert ArrayList to array
        Integer[] array = arrayList.toArray(new Integer[0]);

        // Print the array
        for (Integer element : array) {
            System.out.print(element + " ");
        }
        
        
    }
    
}
