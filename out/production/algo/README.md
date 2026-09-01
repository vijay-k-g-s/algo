# algo
Solve LeetCode Problems

1) Map Counter :
    for(int n : nums){
        map.put(n,map.getOrDefault(n,0)+1);
    }

2) Iterate Map :
    for(Map.Entry<Integer,Integer> entry : map.entrySet()){
        int key = entry.getKey();
        int value = entry.getValue();
    }

3) Quick Sort array :
    Arrays.sort(arr);

4) Array to List :
    List<Integer> list = Arrays.asList(nums);

5) Integer Values :
    Integer.MAX_VALUE 
    Integer.MIN_VALUE

6) String to char array :
    char[] arr = s.toCharArray();

7) Replace alphanumeric characters :
    s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();

8) Return Set to List :
    return new ArrayList<>(set);

9) Use Array as set :
    arr[ch - 'a'];

10) Unique Value for a String :
    String.valueOf(charArray);
