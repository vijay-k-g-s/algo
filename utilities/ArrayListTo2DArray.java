package utilities;
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