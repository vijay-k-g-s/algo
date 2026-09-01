# algo
Java Interview Cheat Sheet — Things to Remember

---

## Collections & Maps

**1) Map Counter**
```java
for (int n : nums) {
    map.put(n, map.getOrDefault(n, 0) + 1);
}
```

**2) Iterate Map**
```java
for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    int key   = entry.getKey();
    int value = entry.getValue();
}
```

**3) Return Set to List**
```java
return new ArrayList<>(set);
```

**4) Array to List**
```java
List<Integer> list = Arrays.asList(nums);
```

---

## Arrays & Sorting

**5) Quick Sort Array**
```java
Arrays.sort(arr);
```

**6) Use Array as Set (lowercase letters only)**
```java
boolean[] seen = new boolean[26];
seen[ch - 'a'] = true;
```

**7) Integer Boundary Values**
```java
Integer.MAX_VALUE   //  2147483647
Integer.MIN_VALUE   // -2147483648
```

---

## Strings

**8) String to Char Array**
```java
char[] arr = s.toCharArray();
```

**9) Unique Key for a String (e.g. anagram grouping)**
```java
Arrays.sort(arr);
String key = String.valueOf(arr);
```

**10) Strip Non-Alphanumeric & Lowercase**
```java
s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
```

---

## Graphs & Trees

**11) Min-Heap (Dijkstra / Prim's)**
```java
PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
// int[] = {cost, node}
pq.offer(new int[]{0, src});
```

**12) BFS Template**
```java
Queue<Integer> queue = new LinkedList<>();
Set<Integer> visited = new HashSet<>();
queue.offer(start);
visited.add(start);
while (!queue.isEmpty()) {
    int node = queue.poll();
    for (int neighbor : graph.get(node)) {
        if (!visited.contains(neighbor)) {
            visited.add(neighbor);
            queue.offer(neighbor);
        }
    }
}
```

**13) DFS Template (iterative)**
```java
Stack<Integer> stack = new Stack<>();
Set<Integer> visited = new HashSet<>();
stack.push(start);
while (!stack.isEmpty()) {
    int node = stack.pop();
    if (visited.contains(node)) continue;
    visited.add(node);
    for (int neighbor : graph.get(node)) {
        stack.push(neighbor);
    }
}
```

**14) Union-Find**
```java
int[] parent = new int[n];
Arrays.fill(parent, -1);

int find(int[] parent, int x) {
    if (parent[x] == -1) return x;
    return parent[x] = find(parent, parent[x]); // path compression
}

void union(int[] parent, int a, int b) {
    int ra = find(parent, a), rb = find(parent, b);
    if (ra != rb) parent[ra] = rb;
}
```

**15) Topological Sort — Kahn's BFS**
```java
int[] indegree = new int[n];
// build graph & fill indegree[]
Queue<Integer> queue = new LinkedList<>();
for (int i = 0; i < n; i++) if (indegree[i] == 0) queue.offer(i);
while (!queue.isEmpty()) {
    int node = queue.poll();
    for (int neighbor : graph.get(node)) {
        if (--indegree[neighbor] == 0) queue.offer(neighbor);
    }
}
```

---

## Sliding Window

**16) Fixed Window**
```java
int sum = 0;
for (int i = 0; i < k; i++) sum += nums[i];
int max = sum;
for (int i = k; i < nums.length; i++) {
    sum += nums[i] - nums[i - k];
    max = Math.max(max, sum);
}
```

**17) Variable Window**
```java
int left = 0;
for (int right = 0; right < nums.length; right++) {
    // expand window with nums[right]
    while (/* window invalid */) {
        // shrink from left
        left++;
    }
    // update answer
}
```

---

## Backtracking

**18) Backtracking Template**
```java
void backtrack(/* state */) {
    if (/* base case */) {
        result.add(new ArrayList<>(current));
        return;
    }
    for (/* choices */) {
        current.add(choice);       // choose
        backtrack(/* next state */); // explore
        current.remove(current.size() - 1); // un-choose
    }
}
```

---

## Binary Search

**19) Standard Binary Search**
```java
int lo = 0, hi = arr.length - 1;
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;  // avoids overflow
    if (arr[mid] == target)      return mid;
    else if (arr[mid] < target)  lo = mid + 1;
    else                         hi = mid - 1;
}
```

---

## Miscellaneous

**20) Infinity Without Overflow**
```java
int INF = Integer.MAX_VALUE / 2;
```

**21) Swap Two Variables**
```java
int temp = a; a = b; b = temp;
```

**22) Char to Int / Int to Char**
```java
int  d = ch - '0';   // char digit → int
char c = (char)('a' + i); // int → char
```

**23) StringBuilder for String Building**
```java
StringBuilder sb = new StringBuilder();
sb.append(ch);
sb.deleteCharAt(sb.length() - 1); // backtrack
String result = sb.toString();
```

**24) 2D Grid — 4-Directional Neighbors**
```java
int[] dr = {0, 0, 1, -1};
int[] dc = {1, -1, 0, 0};
for (int d = 0; d < 4; d++) {
    int nr = row + dr[d];
    int nc = col + dc[d];
    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
        // valid neighbor
    }
}
```
