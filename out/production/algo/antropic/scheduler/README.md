# Dependency System & Task Scheduler

## Files Created

### 1. **SimpleTaskScheduler.java** ⭐ (INTERVIEW VERSION - 15 mins)
**This is the one you want for interviews!**

**Key Features:**
- ✅ Topological Sort (2 methods: DFS & BFS/Kahn's)
- ✅ Cycle Detection (circular dependencies)
- ✅ Parallel execution levels
- ✅ Simple, clean code (~200 lines)

**Core Methods:**
```java
// 1. Check for cycles
boolean hasCycle()

// 2. Get execution order (DFS)
List<Integer> getExecutionOrderDFS()

// 3. Get execution order (BFS - easier for interviews)
List<Integer> getExecutionOrderBFS()

// 4. Get parallel levels (tasks that can run concurrently)
List<List<Integer>> getParallelLevels()
```

**When to use each algorithm:**
- **BFS (Kahn's)**: Easier to code in 15 mins, more intuitive
- **DFS**: More elegant, similar to cycle detection

**Time Complexity:** O(V + E) where V = tasks, E = dependencies
**Space Complexity:** O(V + E)

---

### 2. TaskScheduler.java (Advanced Version)
Full-featured with:
- Task objects with names
- Custom exception for cycles
- Both DFS and Kahn's algorithm
- Detailed cycle path reporting

---

### 3. BuildSystem.java (Real-World Application)
Demonstrates:
- Package dependency management
- Parallel build optimization
- Sequential vs parallel build time
- Transitive dependencies

---

## Interview Template (15 minute code)

```java
// Step 1: Setup
SimpleTaskScheduler scheduler = new SimpleTaskScheduler(numTasks);

// Step 2: Add dependencies
scheduler.addDependency(from, to); // 'from' must complete before 'to'

// Step 3: Check for cycles
if (scheduler.hasCycle()) {
    return null; // Cannot complete
}

// Step 4: Get execution order
List<Integer> order = scheduler.getExecutionOrderBFS();
return order;
```

## Common Interview Questions

### LeetCode 207: Course Schedule
**Question:** Can you finish all courses given prerequisites?
```java
boolean canFinish(int numCourses, int[][] prerequisites) {
    SimpleTaskScheduler scheduler = new SimpleTaskScheduler(numCourses);
    for (int[] pre : prerequisites) {
        scheduler.addDependency(pre[1], pre[0]);
    }
    return !scheduler.hasCycle();
}
```

### LeetCode 210: Course Schedule II
**Question:** Return the order to take courses
```java
int[] findOrder(int numCourses, int[][] prerequisites) {
    SimpleTaskScheduler scheduler = new SimpleTaskScheduler(numCourses);
    for (int[] pre : prerequisites) {
        scheduler.addDependency(pre[1], pre[0]);
    }
    List<Integer> order = scheduler.getExecutionOrderBFS();
    if (order == null) return new int[0];

    int[] result = new int[order.size()];
    for (int i = 0; i < order.size(); i++) {
        result[i] = order.get(i);
    }
    return result;
}
```

## Key Concepts to Remember

### 1. Topological Sort
- Only works on DAG (Directed Acyclic Graph)
- Not unique - multiple valid orders possible
- Used for: build systems, course scheduling, task dependencies

### 2. Cycle Detection
- Use DFS with 3 states: unvisited(0), visiting(1), visited(2)
- If you reach a "visiting" node → cycle found
- Alternative: if topological sort processes < N nodes → cycle exists

### 3. Parallel Levels
- BFS level-by-level processing
- All tasks in same level can run concurrently
- Critical path = number of levels

## How to Run Tests

```bash
# Simple version (interview)
javac scheduler/SimpleTaskScheduler.java
java scheduler.SimpleTaskScheduler

# Advanced versions
javac scheduler/TaskScheduler.java
java scheduler.TaskScheduler

javac scheduler/BuildSystem.java
java scheduler.BuildSystem
```

## Example Output

```
Test 2: Diamond Pattern
    0
   / \
  1   2
   \ /
    3
Has cycle: false
Execution order (BFS): [0, 1, 2, 3]
Parallel levels: [[0], [1, 2], [3]]
```

This shows:
- Level 0: Task 0 runs first
- Level 1: Tasks 1 & 2 can run in parallel
- Level 2: Task 3 runs last
