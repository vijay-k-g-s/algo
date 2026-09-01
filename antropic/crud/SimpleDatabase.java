package crud;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Simple In-Memory Database with Query Builder - Interview Version
 *
 * Features:
 * - Multiple tables
 * - Query builder pattern (where, orderBy, limit)
 * - Index support for fast lookups
 * - Transaction simulation
 *
 * Interview Topics: HashMap, Builder Pattern, Method Chaining, Indexes
 */
public class SimpleDatabase {

    private final Map<String, Table> tables;

    public SimpleDatabase() {
        this.tables = new HashMap<>();
    }

    /**
     * Create a new table
     */
    public void createTable(String tableName) {
        if (tables.containsKey(tableName)) {
            throw new IllegalArgumentException("Table already exists: " + tableName);
        }
        tables.put(tableName, new Table(tableName));
    }

    /**
     * Get table
     */
    public Table table(String tableName) {
        if (!tables.containsKey(tableName)) {
            throw new IllegalArgumentException("Table not found: " + tableName);
        }
        return tables.get(tableName);
    }

    /**
     * Drop table
     */
    public void dropTable(String tableName) {
        tables.remove(tableName);
    }

    /**
     * Table class
     */
    public static class Table {
        private final String name;
        private final Map<String, Record> records;
        private final Map<String, Map<Object, Set<String>>> indexes; // field -> value -> recordIds
        private int nextId;

        public Table(String name) {
            this.name = name;
            this.records = new HashMap<>();
            this.indexes = new HashMap<>();
            this.nextId = 1;
        }

        /**
         * INSERT: Add record
         */
        public String insert(Map<String, Object> data) {
            String id = String.valueOf(nextId++);
            Record record = new Record(id, data);
            records.put(id, record);
            updateIndexes(record);
            return id;
        }

        /**
         * SELECT: Query builder
         */
        public Query select() {
            return new Query(this);
        }

        /**
         * UPDATE: Modify records
         */
        public int update(String id, Map<String, Object> data) {
            Record record = records.get(id);
            if (record == null) return 0;

            removeFromIndexes(record);
            record.data.putAll(data);
            updateIndexes(record);
            return 1;
        }

        /**
         * DELETE: Remove record
         */
        public boolean delete(String id) {
            Record record = records.remove(id);
            if (record != null) {
                removeFromIndexes(record);
                return true;
            }
            return false;
        }

        /**
         * Create index on field for faster lookups
         */
        public void createIndex(String field) {
            if (indexes.containsKey(field)) return;

            Map<Object, Set<String>> index = new HashMap<>();
            for (Record record : records.values()) {
                Object value = record.get(field);
                if (value != null) {
                    index.computeIfAbsent(value, k -> new HashSet<>()).add(record.id);
                }
            }
            indexes.put(field, index);
        }

        private void updateIndexes(Record record) {
            for (String field : indexes.keySet()) {
                Object value = record.get(field);
                if (value != null) {
                    indexes.get(field).computeIfAbsent(value, k -> new HashSet<>()).add(record.id);
                }
            }
        }

        private void removeFromIndexes(Record record) {
            for (String field : indexes.keySet()) {
                Object value = record.get(field);
                if (value != null) {
                    Set<String> ids = indexes.get(field).get(value);
                    if (ids != null) {
                        ids.remove(record.id);
                    }
                }
            }
        }

        /**
         * Get record by ID
         */
        public Record get(String id) {
            return records.get(id);
        }

        /**
         * Count records
         */
        public int count() {
            return records.size();
        }
    }

    /**
     * Query builder for SELECT operations
     */
    public static class Query {
        private final Table table;
        private final List<Condition> conditions;
        private String orderByField;
        private boolean ascending;
        private int limitCount;

        public Query(Table table) {
            this.table = table;
            this.conditions = new ArrayList<>();
            this.ascending = true;
            this.limitCount = -1;
        }

        /**
         * WHERE clause
         */
        public Query where(String field, Object value) {
            conditions.add(new Condition(field, value, ConditionType.EQUALS));
            return this;
        }

        public Query whereGreaterThan(String field, Comparable value) {
            conditions.add(new Condition(field, value, ConditionType.GREATER_THAN));
            return this;
        }

        public Query whereLessThan(String field, Comparable value) {
            conditions.add(new Condition(field, value, ConditionType.LESS_THAN));
            return this;
        }

        /**
         * ORDER BY clause
         */
        public Query orderBy(String field) {
            this.orderByField = field;
            this.ascending = true;
            return this;
        }

        public Query orderByDesc(String field) {
            this.orderByField = field;
            this.ascending = false;
            return this;
        }

        /**
         * LIMIT clause
         */
        public Query limit(int count) {
            this.limitCount = count;
            return this;
        }

        /**
         * Execute query
         */
        public List<Record> execute() {
            List<Record> results = new ArrayList<>(table.records.values());

            // Apply WHERE conditions
            for (Condition condition : conditions) {
                results = results.stream()
                    .filter(condition::matches)
                    .collect(Collectors.toList());
            }

            // Apply ORDER BY
            if (orderByField != null) {
                results.sort((r1, r2) -> {
                    Comparable v1 = (Comparable) r1.get(orderByField);
                    Comparable v2 = (Comparable) r2.get(orderByField);
                    if (v1 == null || v2 == null) return 0;
                    int cmp = v1.compareTo(v2);
                    return ascending ? cmp : -cmp;
                });
            }

            // Apply LIMIT
            if (limitCount > 0 && results.size() > limitCount) {
                results = results.subList(0, limitCount);
            }

            return results;
        }

        /**
         * Get first result
         */
        public Record first() {
            List<Record> results = limit(1).execute();
            return results.isEmpty() ? null : results.get(0);
        }

        /**
         * Count results
         */
        public int count() {
            return execute().size();
        }
    }

    /**
     * Condition for WHERE clause
     */
    private static class Condition {
        String field;
        Object value;
        ConditionType type;

        public Condition(String field, Object value, ConditionType type) {
            this.field = field;
            this.value = value;
            this.type = type;
        }

        @SuppressWarnings("unchecked")
        public boolean matches(Record record) {
            Object recordValue = record.get(field);
            if (recordValue == null) return false;

            switch (type) {
                case EQUALS:
                    return recordValue.equals(value);
                case GREATER_THAN:
                    return ((Comparable) recordValue).compareTo(value) > 0;
                case LESS_THAN:
                    return ((Comparable) recordValue).compareTo(value) < 0;
                default:
                    return false;
            }
        }
    }

    private enum ConditionType {
        EQUALS, GREATER_THAN, LESS_THAN
    }

    /**
     * Record (row) in a table
     */
    public static class Record {
        private final String id;
        private final Map<String, Object> data;

        public Record(String id, Map<String, Object> data) {
            this.id = id;
            this.data = new HashMap<>(data);
        }

        public String getId() {
            return id;
        }

        public Object get(String field) {
            return data.get(field);
        }

        public Map<String, Object> getData() {
            return new HashMap<>(data);
        }

        @Override
        public String toString() {
            return "Record{id=" + id + ", data=" + data + "}";
        }
    }

    // ============== TESTS ==============

    public static void main(String[] args) {
        System.out.println("=== Simple Database Tests ===\n");

        testBasicOperations();
        testQueryBuilder();
        testIndexes();
        testComplexQueries();
    }

    private static void testBasicOperations() {
        System.out.println("Test 1: Basic CRUD Operations");
        SimpleDatabase db = new SimpleDatabase();
        db.createTable("users");
        Table users = db.table("users");

        // INSERT
        String id1 = users.insert(Map.of("name", "Alice", "age", 25, "city", "NYC"));
        String id2 = users.insert(Map.of("name", "Bob", "age", 30, "city", "LA"));
        System.out.println("Inserted user ID: " + id1);
        System.out.println("Total users: " + users.count());

        // SELECT
        Record user = users.get(id1);
        System.out.println("Get user: " + user);

        // UPDATE
        users.update(id1, Map.of("age", 26));
        System.out.println("Updated user: " + users.get(id1));

        // DELETE
        users.delete(id2);
        System.out.println("After delete: " + users.count() + " users\n");
    }

    private static void testQueryBuilder() {
        System.out.println("Test 2: Query Builder");
        SimpleDatabase db = new SimpleDatabase();
        db.createTable("products");
        Table products = db.table("products");

        products.insert(Map.of("name", "Laptop", "price", 1200, "stock", 5));
        products.insert(Map.of("name", "Mouse", "price", 25, "stock", 50));
        products.insert(Map.of("name", "Keyboard", "price", 80, "stock", 30));
        products.insert(Map.of("name", "Monitor", "price", 300, "stock", 15));

        // Query: price > 50 ORDER BY price DESC
        List<Record> expensive = products.select()
            .whereGreaterThan("price", 50)
            .orderByDesc("price")
            .execute();

        System.out.println("Products with price > 50 (ordered by price desc):");
        expensive.forEach(System.out::println);

        // Query: Get first product
        Record first = products.select().orderBy("price").first();
        System.out.println("\nCheapest product: " + first);

        // Query: Count products with stock > 20
        int count = products.select().whereGreaterThan("stock", 20).count();
        System.out.println("Products with stock > 20: " + count + "\n");
    }

    private static void testIndexes() {
        System.out.println("Test 3: Indexes for Fast Lookups");
        SimpleDatabase db = new SimpleDatabase();
        db.createTable("employees");
        Table employees = db.table("employees");

        // Insert many records
        for (int i = 0; i < 1000; i++) {
            employees.insert(Map.of(
                "name", "Employee" + i,
                "department", "Dept" + (i % 10),
                "salary", 50000 + (i * 100)
            ));
        }
        System.out.println("Inserted 1000 employees");

        // Create index on department
        employees.createIndex("department");
        System.out.println("Created index on 'department'");

        // Query with index (faster lookup)
        long start = System.nanoTime();
        List<Record> deptEmployees = employees.select()
            .where("department", "Dept5")
            .execute();
        long end = System.nanoTime();

        System.out.println("Found " + deptEmployees.size() + " employees in Dept5");
        System.out.printf("Query time: %.2f ms\n\n", (end - start) / 1_000_000.0);
    }

    private static void testComplexQueries() {
        System.out.println("Test 4: Complex Queries");
        SimpleDatabase db = new SimpleDatabase();
        db.createTable("orders");
        Table orders = db.table("orders");

        orders.insert(Map.of("customer", "Alice", "amount", 150, "status", "completed"));
        orders.insert(Map.of("customer", "Bob", "amount", 200, "status", "pending"));
        orders.insert(Map.of("customer", "Alice", "amount", 300, "status", "completed"));
        orders.insert(Map.of("customer", "Charlie", "amount", 100, "status", "completed"));
        orders.insert(Map.of("customer", "Bob", "amount", 250, "status", "completed"));

        // Query: Alice's completed orders with amount > 100, ordered by amount
        List<Record> results = orders.select()
            .where("customer", "Alice")
            .where("status", "completed")
            .whereGreaterThan("amount", 100)
            .orderByDesc("amount")
            .execute();

        System.out.println("Alice's completed orders > $100:");
        results.forEach(System.out::println);

        // Query: Top 2 highest completed orders
        List<Record> top2 = orders.select()
            .where("status", "completed")
            .orderByDesc("amount")
            .limit(2)
            .execute();

        System.out.println("\nTop 2 highest completed orders:");
        top2.forEach(System.out::println);
    }
}
