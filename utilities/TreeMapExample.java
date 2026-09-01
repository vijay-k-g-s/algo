
package utilities;
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();

        // Adding entries
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 22);

        // Retrieving values
        System.out.println("Age of Bob: " + map.get("Bob")); 

        // // Iterating over the map in sorted order
        // for (String key : map.keySet()) {
        //     System.out.println(key + ": " + map.get(key));
        // }

    
    }
}