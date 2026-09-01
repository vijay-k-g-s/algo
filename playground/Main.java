// Playground / Scratch: Early experiments with frequency counting using a HashMap.
//   Counts occurrences of each integer, rebuilds a list expanding duplicates,
//   and attempts a sort. Kept for reference — not a complete solution.
//
//package playground;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class Main {
//
//    public static void main(String[] args) {
//        System.out.println("Hello, World!");
//
//        int[] arr = {3,3,3,1};
//
//        for(int i = 0; i < arr.length; i++){
//            System.out.println(arr[i]);
//        }
//
//
//        Map<Integer,Integer> map = new HashMap<>();
//
//        for(int i : arr){
//
//            map.put(i, map.getOrDefault(i, 0) + 1);
//        }
//
//        List<Integer> list = new ArrayList<>();
//
//        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
//            System.out.println(entry.getKey() + " " + entry.getValue());
//
//            if(entry.getValue() > 1){
//                list.add(entry.getKey());
//                list.add(entry.getKey());
//            }else{
//                list.add(entry.getKey());
//            }
//        }
//
//        System.out.println(Arrays.sort(list));
//
//
//    }
//}
