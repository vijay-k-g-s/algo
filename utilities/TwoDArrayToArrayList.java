package utilities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoDArrayToArrayList{
 public static void main(String[] args) {
        Integer[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<List<Integer>> list = new ArrayList<>();

        for (Integer[] subArray : array) {
            list.add(new ArrayList<>(Arrays.asList(subArray)));
        }

        System.out.println(list);
    }
}


