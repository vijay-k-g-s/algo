package binarysearch;

// Problem (LC 981): Design a time-based key-value data structure that stores
//          multiple values for the same key at different timestamps.
//          get(key, timestamp) returns the value with the largest timestamp <= given timestamp.
// Example: set("foo","bar",1); set("foo","bar2",4)
//          get("foo",1) → "bar"
//          get("foo",3) → "bar"   (largest ts <= 3 is ts=1)
//          get("foo",4) → "bar2"
//          get("foo",5) → "bar2"
// Approach: HashMap<String, List<int[]{timestamp, value_index}>>.
//   set: append to list (timestamps always increase, so list is sorted).
//   get: binary search on the list for largest timestamp <= target.
// Time: O(1) set, O(log n) get. Space: O(n)
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKeyValueStore {

    private final Map<String, List<int[]>> map;   // key → [{timestamp, valueIdx}]
    private final List<String> values;

    public TimeBasedKeyValueStore() {
        map = new HashMap<>();
        values = new ArrayList<>();
    }

    public void set(String key, String value, int timestamp) {
        int idx = values.size();
        values.add(value);
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[]{timestamp, idx});
    }

    public String get(String key, int timestamp) {
        List<int[]> list = map.get(key);
        if (list == null) return "";

        int lo = 0, hi = list.size() - 1, ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (list.get(mid)[0] <= timestamp) { ans = list.get(mid)[1]; lo = mid + 1; }
            else hi = mid - 1;
        }
        return ans == -1 ? "" : values.get(ans);
    }

    public static void main(String[] args) {
        TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();
        store.set("foo", "bar", 1);
        store.set("foo", "bar2", 4);
        System.out.println(store.get("foo", 1)); // bar
        System.out.println(store.get("foo", 3)); // bar
        System.out.println(store.get("foo", 4)); // bar2
        System.out.println(store.get("foo", 5)); // bar2
        System.out.println(store.get("foo", 0)); // ""
    }
}
