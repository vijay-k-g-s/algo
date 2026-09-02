# algo
Java Interview Cheat Sheet — NeetCode 150 + Algorithm Practice

---

## Package Index (NeetCode 150)

| Package | Problems | Topics |
|---|---|---|
| `arrays/` | 8 | Contains Duplicate, Two Sum, Group Anagrams, Product Except Self, Valid Sudoku, Longest Consecutive, Encode/Decode |
| `twopointers/` | 4 | Valid Palindrome, Two Sum II, 3Sum, Container With Most Water |
| `slidingwindow/` | 9 | Best Time Stock, Longest Substring, Char Replacement, Permutation in String, Min Window |
| `stack/` | 12 | Valid Parentheses, Min Stack, Generate Parentheses, Car Fleet, Trapping Rain Water, Histogram |
| `binarysearch/` | 7 | Binary Search, Search 2D Matrix, Koko Bananas, Find Min Rotated, Median Two Arrays |
| `linked_list/` | 11 | Reverse, Merge, Reorder, LRU Cache, Cycle Detection, Merge K Lists, Reverse K-Group |
| `tree/` | 15 | Invert, Max Depth, Diameter, LCA, Level Order, Validate BST, Serialize/Deserialize |
| `trie/` | 3 | Implement Trie, Add & Search Words, Word Search II |
| `heap/` | 7 | Kth Largest Stream, K Closest Points, Task Scheduler, Find Median, Design Twitter |
| `backtracking/` | 10 | Subsets, Combination Sum, Permutations, Word Search, Palindrome Partitioning, N-Queens |
| `graph/` | 13 | Islands, Clone Graph, Pacific Atlantic, Course Schedule, Word Ladder, Union-Find |
| `graph/` (advanced) | 6 | Dijkstra, Prim's, Alien Dictionary, Reconstruct Itinerary, Cheapest Flights |
| `dynamicprogramming/` | 23 | Climbing Stairs, House Robber, Coin Change, LIS, LCS, Edit Distance, Burst Balloons |
| `greedy/` | 8 | Max Subarray, Jump Game, Gas Station, Hand of Straights, Partition Labels |
| `interval/` | 6 | Insert, Merge, Non-Overlapping, Meeting Rooms I & II, Min Interval Query |
| `math/` | 8 | Rotate Image, Spiral Matrix, Happy Number, Pow(x,n), Detect Squares |
| `bitmanipulation/` | 7 | Single Number, Hamming Weight, Counting Bits, Reverse Bits, Missing Number |

---

## Java Interview Cheat Sheet — Things to Remember

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

## Two Pointers

**19) Two Pointer — Opposite Ends**
```java
int left = 0, right = nums.length - 1;
while (left < right) {
    if (/* condition */) left++;
    else right--;
}
```

**20) Fast & Slow Pointer (Cycle Detection / Find Middle)**
```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
// slow is now at the middle
```

---

## Binary Search

**21) Standard Binary Search**
```java
int lo = 0, hi = arr.length - 1;
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;  // avoids overflow
    if (arr[mid] == target)      return mid;
    else if (arr[mid] < target)  lo = mid + 1;
    else                         hi = mid - 1;
}
```

**22) Binary Search on Answer (Search Space)**
```java
// Use when: "find minimum/maximum X such that condition(X) is true"
int lo = minPossible, hi = maxPossible;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (condition(mid)) hi = mid;    // mid works, try smaller
    else                lo = mid + 1; // mid too small
}
return lo;
```

**23) Find First / Last Occurrence (Biased Binary Search)**
```java
// First occurrence: bias LEFT on match
if (arr[mid] == target) { result = mid; hi = mid - 1; }

// Last occurrence: bias RIGHT on match
if (arr[mid] == target) { result = mid; lo = mid + 1; }
```

---

## Linked List

**24) Dummy Head Node**
```java
ListNode dummy = new ListNode(0);
ListNode curr = dummy;
// build list by attaching to curr.next
return dummy.next;
```

**25) Reverse a Linked List**
```java
ListNode prev = null, curr = head;
while (curr != null) {
    ListNode next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}
return prev; // new head
```

---

## Dynamic Programming

**26) 1-D DP Template**
```java
int[] dp = new int[n + 1];
dp[0] = baseCase;
for (int i = 1; i <= n; i++) {
    dp[i] = /* recurrence using dp[i-1], dp[i-2], etc. */;
}
return dp[n];
```

**27) 2-D DP Template**
```java
int[][] dp = new int[m + 1][n + 1];
// initialize base cases
for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
        if (/* match */) dp[i][j] = dp[i-1][j-1] + 1;
        else             dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
    }
}
return dp[m][n];
```

**28) Memoization (Top-Down DP)**
```java
Map<String, Integer> memo = new HashMap<>();

int solve(/* state params */) {
    String key = /* encode state */;
    if (memo.containsKey(key)) return memo.get(key);
    int result = /* recursive calls */;
    memo.put(key, result);
    return result;
}
```

**29) 0/1 Knapsack (each item used at most once — iterate BACKWARD)**
```java
boolean[] dp = new boolean[target + 1];
dp[0] = true;
for (int num : nums) {
    for (int j = target; j >= num; j--) { // backward prevents reuse
        dp[j] |= dp[j - num];
    }
}
```

**30) Unbounded Knapsack (items reusable — iterate FORWARD)**
```java
int[] dp = new int[amount + 1];
dp[0] = 1;
for (int coin : coins) {
    for (int j = coin; j <= amount; j++) { // forward allows reuse
        dp[j] += dp[j - coin];
    }
}
```

**31) Kadane's Algorithm (Maximum Subarray)**
```java
int curr = nums[0], maxSum = nums[0];
for (int i = 1; i < nums.length; i++) {
    curr = Math.max(nums[i], curr + nums[i]);
    maxSum = Math.max(maxSum, curr);
}
```

**32) Prefix Sum**
```java
int[] prefix = new int[nums.length + 1];
for (int i = 0; i < nums.length; i++) {
    prefix[i + 1] = prefix[i] + nums[i];
}
// sum of nums[l..r] = prefix[r+1] - prefix[l]
```

---

## Monotonic Stack

**33) Monotonic Decreasing Stack (Next Greater Element)**
```java
// Scan right→left; stack maintains decreasing values
Stack<Integer> stack = new Stack<>();
for (int i = n - 1; i >= 0; i--) {
    while (!stack.isEmpty() && stack.peek() <= nums[i]) stack.pop();
    result[i] = stack.isEmpty() ? -1 : stack.peek();
    stack.push(nums[i]);
}
```

**34) Monotonic Increasing Stack (Next Smaller Element)**
```java
// Scan right→left; stack maintains increasing values
Stack<Integer> stack = new Stack<>();
for (int i = n - 1; i >= 0; i--) {
    while (!stack.isEmpty() && stack.peek() >= nums[i]) stack.pop();
    result[i] = stack.isEmpty() ? -1 : stack.peek();
    stack.push(nums[i]);
}
```

---

## Heap / Priority Queue

**35) Max-Heap**
```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
// or: new PriorityQueue<>((a, b) -> b - a);
```

**36) Min-Heap of size k (keep k largest)**
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int n : nums) {
    minHeap.offer(n);
    if (minHeap.size() > k) minHeap.poll(); // evict smallest
}
// minHeap.peek() = kth largest
```

**37) Two Heaps (Running Median)**
```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // lower half
PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // upper half
// Invariant: maxHeap.size() == minHeap.size() or maxHeap.size() == minHeap.size() + 1
```

---

## Intervals

**38) Interval Sort + Sweep**
```java
Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // sort by start
// or sort by end: (a, b) -> a[1] - b[1]
```

**39) Merge Overlapping Intervals**
```java
// After sorting by start:
if (current[0] <= last[1]) last[1] = Math.max(last[1], current[1]); // merge
else result.add(current); // no overlap, start new
```

---

## Bit Manipulation

**40) Common Bit Operations**
```java
n & 1          // check if odd (last bit)
n >> 1         // divide by 2 (right shift)
n << 1         // multiply by 2 (left shift)
n & (n - 1)    // clear lowest set bit
n | (1 << i)   // set bit i
n & ~(1 << i)  // clear bit i
n ^ (1 << i)   // toggle bit i
n & (-n)       // isolate lowest set bit
a ^ b          // XOR: same bits cancel → finds the unique element
```

**41) Count Set Bits**
```java
int count = 0;
while (n != 0) { n &= (n - 1); count++; } // Brian Kernighan's algorithm
```

**42) Counting Bits DP**
```java
int[] ans = new int[n + 1];
for (int i = 1; i <= n; i++) ans[i] = ans[i >> 1] + (i & 1);
```

---

## Math & Number Theory

**43) GCD (Euclidean Algorithm)**
```java
int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
int lcm(int a, int b) { return a / gcd(a, b) * b; } // avoids overflow
```

**44) Fast Exponentiation**
```java
double fastPow(double x, long n) {
    if (n == 0) return 1.0;
    if (n % 2 == 1) return x * fastPow(x, n - 1);
    return fastPow(x * x, n / 2);
}
```

**45) Check Prime**
```java
boolean isPrime(int n) {
    if (n < 2) return false;
    for (int i = 2; i * i <= n; i++) if (n % i == 0) return false;
    return true;
}
```

---

## Graph

**46) Dijkstra's Algorithm**
```java
int[] dist = new int[n];
Arrays.fill(dist, Integer.MAX_VALUE);
dist[src] = 0;
PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
pq.offer(new int[]{0, src}); // {cost, node}
while (!pq.isEmpty()) {
    int[] curr = pq.poll();
    int cost = curr[0], node = curr[1];
    if (cost > dist[node]) continue; // stale entry
    for (int[] neighbor : graph.get(node)) {
        int newCost = cost + neighbor[1];
        if (newCost < dist[neighbor[0]]) {
            dist[neighbor[0]] = newCost;
            pq.offer(new int[]{newCost, neighbor[0]});
        }
    }
}
```

**47) Union-Find with Rank**
```java
int[] parent, rank;
void init(int n) { parent = new int[n]; rank = new int[n]; for (int i = 0; i < n; i++) parent[i] = i; }
int find(int x) { if (parent[x] != x) parent[x] = find(parent[x]); return parent[x]; }
boolean union(int a, int b) {
    int ra = find(a), rb = find(b);
    if (ra == rb) return false; // already connected
    if (rank[ra] < rank[rb]) { int t = ra; ra = rb; rb = t; }
    parent[rb] = ra;
    if (rank[ra] == rank[rb]) rank[ra]++;
    return true;
}
```

---

## Trie

**48) Trie Node & Insert**
```java
class TrieNode { TrieNode[] children = new TrieNode[26]; boolean isEnd; }
TrieNode root = new TrieNode();

void insert(String word) {
    TrieNode node = root;
    for (char c : word.toCharArray()) {
        int i = c - 'a';
        if (node.children[i] == null) node.children[i] = new TrieNode();
        node = node.children[i];
    }
    node.isEnd = true;
}
```

---

## Collections Tricks

**49) Deque as Stack or Queue**
```java
Deque<Integer> deque = new ArrayDeque<>();
deque.push(x);     // push to front (stack)
deque.pop();       // pop from front (stack)
deque.offer(x);    // add to back (queue)
deque.poll();      // remove from front (queue)
deque.peekFirst(); // see front
deque.peekLast();  // see back
```

**50) LinkedHashMap for LRU (insertion/access order)**
```java
// Access-order LRU cache (evict least recently accessed):
Map<Integer, Integer> cache = new LinkedHashMap<>(capacity, 0.75f, true) {
    protected boolean removeEldestEntry(Map.Entry e) { return size() > capacity; }
};
```

**51) TreeMap — Ceiling / Floor / Range**
```java
TreeMap<Integer, Integer> map = new TreeMap<>();
map.ceilingKey(x);  // smallest key >= x
map.floorKey(x);    // largest key <= x
map.higherKey(x);   // smallest key > x
map.lowerKey(x);    // largest key < x
map.firstKey();     // minimum key
map.lastKey();      // maximum key
```

**52) Frequency Map Shorthand**
```java
map.merge(key, 1, Integer::sum);          // increment
map.merge(key, -1, Integer::sum);         // decrement
map.getOrDefault(key, 0);                 // safe get
map.computeIfAbsent(key, k -> new ArrayList<>()).add(val); // grouping
```

---

## Miscellaneous

**53) Infinity Without Overflow**
```java
int INF = Integer.MAX_VALUE / 2;
```

**54) Swap Two Variables**
```java
int temp = a; a = b; b = temp;
```

**55) Char to Int / Int to Char**
```java
int  d = ch - '0';            // char digit → int
char c = (char)('a' + i);     // int → lowercase char
int  n = Character.getNumericValue(ch); // handles hex too
```

**56) StringBuilder for String Building**
```java
StringBuilder sb = new StringBuilder();
sb.append(ch);
sb.deleteCharAt(sb.length() - 1); // backtrack / remove last
sb.reverse();                      // reverse in-place
String result = sb.toString();
```

**57) 2D Grid — 4-Directional Neighbors**
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

**58) Sort 2D Array by Column**
```java
Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // sort by first column
Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // sort by second column
```

**59) Convert int[] ↔ Integer[] ↔ List**
```java
// int[] → List<Integer>
List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

// List<Integer> → int[]
int[] arr = list.stream().mapToInt(Integer::intValue).toArray();

// int[] → Integer[]
Integer[] boxed = Arrays.stream(arr).boxed().toArray(Integer[]::new);
```

**60) String Split & Join**
```java
String[] parts = s.split(",");           // split by delimiter
String joined = String.join("-", parts); // join with delimiter
String trimmed = s.strip();              // remove leading/trailing whitespace
```
