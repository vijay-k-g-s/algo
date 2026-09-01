package utilities;

// Utility: Convert List<List<Integer>> → Integer[][] (2D array).
// Example: [[1,2,3],[4,5,6],[7,8,9]]  →  Integer[3][3] with same values
// Idiom: array[i] = subList.toArray(new Integer[0])
//   The `new Integer[0]` hint tells toArray the target element type.
//   Java infers the correct size — passing length 0 is idiomatic and efficient.
// Note: result is Integer[][] (boxed), not int[][] (primitive).
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.List;

public class ArrayListTo2DArray {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>(List.of(1, 2, 3)));
        list.add(new ArrayList<>(List.of(4, 5, 6)));
        list.add(new ArrayList<>(List.of(7, 8, 9)));

        Integer[][] array = new Integer[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> subList = list.get(i);
            array[i] = subList.toArray(new Integer[0]);
        }

        // Print the 2D array
        for (Integer[] subArray : array) {
            for (Integer num : subArray) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}