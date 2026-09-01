package utilities;

// Utility: Convert ArrayList<Integer> → Integer[] (1D array).
// Example: ArrayList [1, 2, 3]  →  Integer[] {1, 2, 3}
// Idiom: list.toArray(new Integer[0])
//   Passing `new Integer[0]` tells toArray the element type.
//   Java automatically sizes the result array to list.size().
// Note: produces Integer[] (boxed). For int[] use streams:
//   list.stream().mapToInt(Integer::intValue).toArray()
//
// ─────────────────────────────────────────────────────────────────────────────

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
