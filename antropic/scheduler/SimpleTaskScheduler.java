package scheduler;

import java.util.*;

/**
 * Simple Task Scheduler - Interview Version (15 min coding)
 *
 * Core Concepts:
 * 1. Topological Sort (execution order)
 * 2. Cycle Detection (circular dependencies)
 * 3. Graph representation with adjacency list
 *
 * LeetCode Similar: Course Schedule I & II
 */
public class SimpleTaskScheduler {

    private int numTasks;
    private List<List<Integer>> graph;  // adjacency list

    /**
     * Constructor
     * @param n - number of tasks (0 to n-1)
     */
    public SimpleTaskScheduler(int n) {
        this.numTasks = n;
        this.graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    }

    /**
     * Add dependency: task 'from' must complete before task 'to'
     * @param from - prerequisite task
     * @param to - dependent task
     */
    public void addDependency(int from, int to) {
        graph.get(from).add(to);
    }

    /**
     * Check if there's a cycle (Method 1: DFS)
     * @return true if cycle exists
     */
    public boolean hasCycle() {
        int[] state = new int[numTasks];  // 0=unvisited, 1=visiting, 2=visited

        for (int i = 0; i < numTasks; i++) {
            if (state[i] == 0) {
                if (hasCycleDFS(i, state)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleDFS(int task, int[] state) {
        if (state[task] == 1) return true;   // Back edge found = cycle
        if (state[task] == 2) return false;  // Already processed

        state[task] = 1;  // Mark as visiting

        for (int next : graph.get(task)) {
            if (hasCycleDFS(next, state)) {
                return true;
            }
        }

        state[task] = 2;  // Mark as visited
        return false;
    }

    /**
     * Get execution order using Topological Sort (Method 1: DFS)
     * @return List of tasks in execution order, or null if cycle exists
     */
    public List<Integer> getExecutionOrderDFS() {
        if (hasCycle()) return null;

        boolean[] visited = new boolean[numTasks];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < numTasks; i++) {
            if (!visited[i]) {
                topologicalSortDFS(i, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private void topologicalSortDFS(int task, boolean[] visited, Stack<Integer> stack) {
        visited[task] = true;

        for (int next : graph.get(task)) {
            if (!visited[next]) {
                topologicalSortDFS(next, visited, stack);
            }
        }

        stack.push(task);  // Add to stack after processing all dependencies
    }

    /**
     * Get execution order using Kahn's Algorithm (Method 2: BFS)
     * Easier to understand and implement in interview
     * @return List of tasks in execution order, or null if cycle exists
     */
    public List<Integer> getExecutionOrderBFS() {
        // Step 1: Calculate in-degree for each task
        int[] inDegree = new int[numTasks];
        for (int i = 0; i < numTasks; i++) {
            for (int next : graph.get(i)) {
                inDegree[next]++;
            }
        }

        // Step 2: Add all tasks with in-degree 0 to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numTasks; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 3: Process tasks level by level
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            // Reduce in-degree for neighbors
            for (int next : graph.get(current)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // Step 4: Check if all tasks were processed
        return result.size() == numTasks ? result : null;
    }

    /**
     * Get parallel execution levels (tasks that can run concurrently)
     * @return List of levels, each level contains tasks that can run in parallel
     */
    public List<List<Integer>> getParallelLevels() {
        // Calculate in-degree
        int[] inDegree = new int[numTasks];
        for (int i = 0; i < numTasks; i++) {
            for (int next : graph.get(i)) {
                inDegree[next]++;
            }
        }

        // BFS level by level
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numTasks; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<List<Integer>> levels = new ArrayList<>();
        int processedTasks = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                currentLevel.add(current);
                processedTasks++;

                for (int next : graph.get(current)) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }

            levels.add(currentLevel);
        }

        return processedTasks == numTasks ? levels : null;
    }

    // ============== TEST CASES ==============

    public static void main(String[] args) {
        System.out.println("=== Simple Task Scheduler (Interview Version) ===\n");

        test1_LinearDependencies();
        test2_DiamondPattern();
        test3_CircularDependency();
        test4_ParallelLevels();
        test5_LeetCodeExample();
    }

    private static void test1_LinearDependencies() {
        System.out.println("Test 1: Linear Dependencies (0->1->2->3)");
        SimpleTaskScheduler scheduler = new SimpleTaskScheduler(4);
        scheduler.addDependency(0, 1);
        scheduler.addDependency(1, 2);
        scheduler.addDependency(2, 3);

        System.out.println("Has cycle: " + scheduler.hasCycle());
        System.out.println("Execution order (DFS): " + scheduler.getExecutionOrderDFS());
        System.out.println("Execution order (BFS): " + scheduler.getExecutionOrderBFS());
        System.out.println();
    }

    private static void test2_DiamondPattern() {
        System.out.println("Test 2: Diamond Pattern");
        System.out.println("    0");
        System.out.println("   / \\");
        System.out.println("  1   2");
        System.out.println("   \\ /");
        System.out.println("    3");

        SimpleTaskScheduler scheduler = new SimpleTaskScheduler(4);
        scheduler.addDependency(0, 1);
        scheduler.addDependency(0, 2);
        scheduler.addDependency(1, 3);
        scheduler.addDependency(2, 3);

        System.out.println("Has cycle: " + scheduler.hasCycle());
        System.out.println("Execution order (BFS): " + scheduler.getExecutionOrderBFS());
        System.out.println("Parallel levels: " + scheduler.getParallelLevels());
        System.out.println();
    }

    private static void test3_CircularDependency() {
        System.out.println("Test 3: Circular Dependency (0->1->2->0)");
        SimpleTaskScheduler scheduler = new SimpleTaskScheduler(3);
        scheduler.addDependency(0, 1);
        scheduler.addDependency(1, 2);
        scheduler.addDependency(2, 0);  // Creates cycle

        System.out.println("Has cycle: " + scheduler.hasCycle());
        System.out.println("Execution order: " + scheduler.getExecutionOrderBFS());
        System.out.println();
    }

    private static void test4_ParallelLevels() {
        System.out.println("Test 4: Multiple Parallel Opportunities");
        System.out.println("Level 0: tasks 0, 1, 2 (no dependencies)");
        System.out.println("Level 1: tasks 3, 4 (depend on 0)");
        System.out.println("Level 2: task 5 (depends on 3, 4)");

        SimpleTaskScheduler scheduler = new SimpleTaskScheduler(6);
        // Level 0: 0, 1, 2
        // Level 1: 3, 4
        scheduler.addDependency(0, 3);
        scheduler.addDependency(0, 4);
        // Level 2: 5
        scheduler.addDependency(3, 5);
        scheduler.addDependency(4, 5);

        List<List<Integer>> levels = scheduler.getParallelLevels();
        System.out.println("Parallel levels: " + levels);
        for (int i = 0; i < levels.size(); i++) {
            System.out.println("  Level " + i + ": " + levels.get(i));
        }
        System.out.println();
    }

    private static void test5_LeetCodeExample() {
        System.out.println("Test 5: LeetCode Course Schedule Example");
        System.out.println("numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]");

        SimpleTaskScheduler scheduler = new SimpleTaskScheduler(4);
        // Must take course 0 before course 1
        scheduler.addDependency(0, 1);
        // Must take course 0 before course 2
        scheduler.addDependency(0, 2);
        // Must take course 1 before course 3
        scheduler.addDependency(1, 3);
        // Must take course 2 before course 3
        scheduler.addDependency(2, 3);

        System.out.println("Can finish all courses: " + !scheduler.hasCycle());
        System.out.println("Course order: " + scheduler.getExecutionOrderBFS());
        System.out.println();
    }
}
