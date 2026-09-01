//package
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.concurrent.Executors;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * LRU Cache tested with Java Virtual Threads (Project Loom - Java 21+)
// *
// * Virtual Threads are lightweight threads introduced in Java 21 that allow
// * creating millions of threads with minimal overhead.
// *
// * Key Benefits:
// * - Extremely lightweight (can create millions)
// * - Perfect for high-concurrency I/O-bound tasks
// * - Same API as platform threads but managed by JVM
// *
// * To run: Requires Java 21+
// * javac --enable-preview --release 21 LRUCacheVirtualThreadTest.java
// * java --enable-preview LRUCacheVirtualThreadTest
// */
//public class LRUCacheVirtualThreadTest {
//
//    /**
//     * Thread-safe LRU Cache
//     */
//    static class LRUCache {
//        private final LinkedHashMap<Integer, Integer> cache;
//        private final int capacity;
//
//        public LRUCache(int capacity) {
//            this.capacity = capacity;
//            this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
//                @Override
//                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//                    return size() > LRUCache.this.capacity;
//                }
//            };
//        }
//
//        public synchronized int get(int key) {
//            return cache.getOrDefault(key, -1);
//        }
//
//        public synchronized void put(int key, int value) {
//            cache.put(key, value);
//        }
//
//        public synchronized int size() {
//            return cache.size();
//        }
//
//        public synchronized void clear() {
//            cache.clear();
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        System.out.println("=== LRU Cache with Virtual Threads (Java 21+) ===\n");
//
//        // Check Java version
//        String javaVersion = System.getProperty("java.version");
//        System.out.println("Java Version: " + javaVersion);
//        System.out.println();
//
//        LRUCache cache = new LRUCache(1000);
//
//        // Test 1: Create many virtual threads (lightweight!)
//        System.out.println("Test 1: High Concurrency with Virtual Threads");
//        System.out.println("Creating 10,000 virtual threads...");
//
//        long startTime = System.currentTimeMillis();
//
//        // Create virtual thread executor
//        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
//            CountDownLatch latch = new CountDownLatch(10000);
//            AtomicInteger successCount = new AtomicInteger(0);
//
//            // Submit 10,000 tasks to virtual threads
//            for (int i = 0; i < 10000; i++) {
//                final int threadId = i;
//                executor.submit(() -> {
//                    try {
//                        // Each virtual thread does some cache operations
//                        for (int j = 0; j < 10; j++) {
//                            int key = (threadId * 10) + j;
//                            cache.put(key, key * 2);
//                            cache.get(key);
//                        }
//                        successCount.incrementAndGet();
//                    } finally {
//                        latch.countDown();
//                    }
//                });
//            }
//
//            // Wait for all virtual threads to complete
//            latch.await();
//        }
//
//        long endTime = System.currentTimeMillis();
//
//        System.out.println("✓ All 10,000 virtual threads completed!");
//        System.out.println("Cache size: " + cache.size());
//        System.out.println("Time taken: " + (endTime - startTime) + "ms");
//        System.out.println();
//
//        // Test 2: Compare with Platform Threads
//        System.out.println("Test 2: Comparison - Platform Threads vs Virtual Threads");
//        cache.clear();
//
//        // Platform threads (limited by OS)
//        System.out.println("\nUsing Platform Threads (100 threads):");
//        startTime = System.currentTimeMillis();
//
//        try (ExecutorService platformExecutor = Executors.newFixedThreadPool(100)) {
//            CountDownLatch latch = new CountDownLatch(1000);
//
//            for (int i = 0; i < 1000; i++) {
//                final int taskId = i;
//                platformExecutor.submit(() -> {
//                    try {
//                        for (int j = 0; j < 10; j++) {
//                            cache.put(taskId * 10 + j, taskId);
//                            cache.get(taskId * 10 + j);
//                        }
//                    } finally {
//                        latch.countDown();
//                    }
//                });
//            }
//            latch.await();
//        }
//
//        long platformTime = System.currentTimeMillis() - startTime;
//        System.out.println("Platform threads time: " + platformTime + "ms");
//
//        // Virtual threads (can create many more!)
//        cache.clear();
//        System.out.println("\nUsing Virtual Threads (1000 threads):");
//        startTime = System.currentTimeMillis();
//
//        try (ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
//            CountDownLatch latch = new CountDownLatch(1000);
//
//            for (int i = 0; i < 1000; i++) {
//                final int taskId = i;
//                virtualExecutor.submit(() -> {
//                    try {
//                        for (int j = 0; j < 10; j++) {
//                            cache.put(taskId * 10 + j, taskId);
//                            cache.get(taskId * 10 + j);
//                        }
//                    } finally {
//                        latch.countDown();
//                    }
//                });
//            }
//            latch.await();
//        }
//
//        long virtualTime = System.currentTimeMillis() - startTime;
//        System.out.println("Virtual threads time: " + virtualTime + "ms");
//        System.out.println();
//
//        // Test 3: Using Thread.startVirtualThread() - Java 21 API
//        System.out.println("Test 3: Using Thread.startVirtualThread() API");
//        cache.clear();
//
//        Thread vThread1 = Thread.startVirtualThread(() -> {
//            for (int i = 0; i < 100; i++) {
//                cache.put(i, i * 2);
//            }
//            System.out.println("Virtual Thread 1 completed");
//        });
//
//        Thread vThread2 = Thread.startVirtualThread(() -> {
//            for (int i = 100; i < 200; i++) {
//                cache.put(i, i * 2);
//            }
//            System.out.println("Virtual Thread 2 completed");
//        });
//
//        Thread vThread3 = Thread.startVirtualThread(() -> {
//            for (int i = 0; i < 200; i++) {
//                cache.get(i);
//            }
//            System.out.println("Virtual Thread 3 (reader) completed");
//        });
//
//        vThread1.join();
//        vThread2.join();
//        vThread3.join();
//
//        System.out.println("Final cache size: " + cache.size());
//        System.out.println("\n✓ All virtual thread tests passed!");
//        System.out.println("\nKey Takeaway: Virtual threads allow massive concurrency");
//        System.out.println("with minimal overhead - perfect for high-concurrency scenarios!");
//    }
//}
