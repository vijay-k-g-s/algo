# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java 17 IntelliJ IDEA project (`algo.iml`) for solving LeetCode and algorithm problems. No build tool (Maven/Gradle) is used — the project is managed directly by IntelliJ with compiled output in `out/production/algo/`.

## Building and Running

**Compile a single file (from project root):**
```bash
javac -d out/production/algo -sourcepath . graph/NetworkDelayTimeDIJKSTRAS.java
```

**Compile all files in a package:**
```bash
javac -d out/production/algo -sourcepath . graph/*.java
```

**Run a class:**
```bash
java -cp out/production/algo graph.NetworkDelayTimeDIJKSTRASDIJKSTRAS
```

**`antropic/` packages use flat package names** (not path-based), so compile from the directory containing the package root:
```bash
# LRU cache: package cache (files in antropic/LRU/cache/)
javac -d out/production/algo -sourcepath antropic/LRU antropic/LRU/cache/LRUCache.java
java -cp out/production/algo cache.LRUCache

# CRUD: package crud (files in antropic/crud/)
javac -d out/production/algo -sourcepath antropic antropic/crud/InMemoryCRUD.java
java -cp out/production/algo crud.InMemoryCRUD

# Scheduler: package scheduler (files in antropic/scheduler/)
javac -d out/production/algo -sourcepath antropic antropic/scheduler/SimpleTaskScheduler.java
java -cp out/production/algo scheduler.SimpleTaskScheduler
```

Each class has a `main()` method with inline test cases — there is no separate test framework.

## Code Structure

Packages are organized by algorithm topic, each a directory at the project root:

| Package | Contents |
|---|---|
| `graph/` | BFS, DFS, Union-Find, Dijkstra, Topological Sort, matrix island problems |
| `tree/` | Binary tree problems |
| `heap/` | Priority queue / top-K problems |
| `stack/` | Monotonic stack problems |
| `linked_list/` | Linked list problems |
| `binarysearch/` | Binary search variants |
| `slidingwindow/` | Fixed and variable sliding window problems |
| `backtracking/` | Backtracking problems |
| `interval/` | Interval scheduling/merging |
| `utilities/` | Shared conversion helpers (array↔list, 2D array↔ArrayList) |
| `antropic/` | Production-style implementations: LRU Cache (thread-safe variants), in-memory CRUD, task scheduler |
| `recursion/` | Basic recursion examples |
| `playground/` | Scratch/scratch experimentation |

## Graph Package Architecture

`Graph.java` — undirected adjacency list (`Map<Integer, List<Integer>>`); `DirectedGraph.java` — directed variant. Both are used by traversal classes (BFS/DFS).

Matrix-based graph problems (islands, water flow) do not use `Graph.java` — they operate directly on `int[][]` grids with 4-directional neighbor expansion.

**Algorithms implemented:**
- Traversal: `BFS`, `DFS`, `BFSDisconnected`, `DFSDisconnected`, `CloneGraph`
- Cycle detection: directed (`CycleDetectionDirected`), undirected DFS/BFS, topological sort variant (`CycleDetectionTopoSort`)
- Topological sort: DFS post-order (`TopologicalSortDFS`), Kahn's BFS (`TopologicalSortBFS`); applied in `CourseSchedule1`, `CourseSchedule2`, `AlienDictionary`
- Union-Find: `RedundantConnectionUnionFind`, `GraphValidTreeUnionFind`, `NumberOfConnectedComponents`
- Shortest path: Dijkstra (`NetworkDelayTime`, `PathWithMinimumEffort`, `SwimInRisingWater`), Bellman-Ford variant (`CheapestFlightsWithinKStops`)
- MST: Prim's algorithm (`MinCostConnectPoints`) — edge costs computed on the fly for dense graphs
- Multi-source BFS: `IslandsAndTreasureMultiSourceBFS`, `RottingFruitMultiSourceBFS`
- Matrix problems: `NumberOfIslands`, `MaxAreaOfIsland`, `SurroundedRegions`, `PacificAtlanticWaterFlow`

## Common Java Patterns (from README)

Frequently used idioms in this codebase:
- **Map counter:** `map.put(n, map.getOrDefault(n, 0) + 1)`
- **Char array as set:** `arr[ch - 'a']` (26-element boolean/int array for lowercase letters)
- **Unique key for a string:** `String.valueOf(charArray)` (e.g. after sorting chars for anagram grouping)
- **Alphanumeric normalization:** `s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase()`
- **Set → List:** `new ArrayList<>(set)`

## Conventions

- Each file solves one problem. A block comment at the top describes the problem, approach, and time/space complexity.
- Test cases with expected output are in `main()`.
- Graph adjacency lists use `Map<Integer, List<Integer>>` or `Map<Integer, List<int[]>>` (for weighted edges where `int[] = {neighbor, weight}`).
- Min-heap for Dijkstra/Prim's: `PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]))`.
- `Integer.MAX_VALUE / 2` is used as infinity to avoid overflow when initializing distance arrays.
- Directed cycle detection uses 3-state DFS: `0` = unvisited (WHITE), `1` = in stack (GRAY), `2` = done (BLACK). Used in `CycleDetectionDirected` and `AlienDictionary`.
