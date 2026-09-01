# Mini CRUD Engine - Interview Practice

## Files Created

### 1. **InMemoryCRUD.java** ⭐ (BASIC - 15 mins)
Simple CRUD with generic types

**Core Operations:**
```java
T create(T entity)                    // Create new entity
Optional<T> read(String id)           // Read by ID
List<T> readAll()                     // Read all
List<T> filter(Predicate<T> filter)   // Filter with lambda
Optional<T> update(String id, T data) // Update entity
boolean delete(String id)             // Delete entity
```

**Key Features:**
- ✅ Generic type with Entity interface
- ✅ Auto-generated IDs
- ✅ Functional filtering with Predicates
- ✅ Optional for null safety
- ✅ O(1) operations with HashMap

**Time Complexity:**
- Create: O(1)
- Read: O(1)
- Update: O(1)
- Delete: O(1)
- Filter: O(n)

---

### 2. **SimpleDatabase.java** ⭐ (ADVANCED - 20 mins)
In-memory database with query builder

**Core Features:**
```java
// Table operations
createTable(String name)
table(String name).insert(Map<String, Object> data)

// Query builder pattern
table.select()
    .where("field", value)
    .whereGreaterThan("age", 25)
    .whereLessThan("price", 100)
    .orderBy("field")
    .orderByDesc("field")
    .limit(10)
    .execute()

// Indexes for fast lookups
table.createIndex("field")
```

**Key Features:**
- ✅ Multiple tables
- ✅ Query builder (method chaining)
- ✅ WHERE, ORDER BY, LIMIT support
- ✅ Index support for optimization
- ✅ Flexible schema (Map-based)

---

## Interview Template - Basic CRUD (15 mins)

```java
// Step 1: Define Entity interface
interface Entity {
    String getId();
    void setId(String id);
}

// Step 2: Create CRUD class
class InMemoryCRUD<T extends Entity> {
    private Map<String, T> storage = new HashMap<>();
    private int nextId = 1;

    // CREATE
    public T create(T entity) {
        if (entity.getId() == null) {
            entity.setId(String.valueOf(nextId++));
        }
        storage.put(entity.getId(), entity);
        return entity;
    }

    // READ
    public Optional<T> read(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    // UPDATE
    public Optional<T> update(String id, T entity) {
        if (!storage.containsKey(id)) return Optional.empty();
        entity.setId(id);
        storage.put(id, entity);
        return Optional.of(entity);
    }

    // DELETE
    public boolean delete(String id) {
        return storage.remove(id) != null;
    }
}
```

---

## Interview Template - Query Builder (20 mins)

```java
class SimpleDatabase {
    private Map<String, Table> tables = new HashMap<>();

    class Table {
        private Map<String, Record> records = new HashMap<>();

        public String insert(Map<String, Object> data) {
            String id = UUID.randomUUID().toString();
            records.put(id, new Record(id, data));
            return id;
        }

        public Query select() {
            return new Query(this);
        }
    }

    class Query {
        private Table table;
        private List<Condition> conditions = new ArrayList<>();

        public Query where(String field, Object value) {
            conditions.add(new Condition(field, value));
            return this;
        }

        public List<Record> execute() {
            return table.records.values().stream()
                .filter(r -> conditions.stream().allMatch(c -> c.matches(r)))
                .collect(Collectors.toList());
        }
    }
}
```

---

## Common Interview Questions

### Q1: Design an in-memory key-value store
**Answer:** Use `InMemoryCRUD.java` approach
- HashMap for O(1) access
- Generic types for flexibility
- Optional for null safety

### Q2: Design a SQL-like query interface
**Answer:** Use `SimpleDatabase.java` approach
- Builder pattern for fluent API
- Stream API for filtering
- Comparable for ordering

### Q3: How to optimize lookups?
**Answer:** Add indexes
```java
// Create index on frequently queried fields
table.createIndex("email");  // O(1) lookup instead of O(n)
```

### Q4: How to handle transactions?
**Answer:** Snapshot + rollback pattern
```java
Map<String, T> snapshot = new HashMap<>(storage);
try {
    // operations
} catch (Exception e) {
    storage = snapshot; // rollback
}
```

---

## Key Concepts Tested

### 1. Data Structures
- **HashMap**: O(1) operations
- **ArrayList**: Ordered collection
- **HashSet**: Unique values for indexes

### 2. Design Patterns
- **Generic Types**: Type safety with `<T extends Entity>`
- **Builder Pattern**: Method chaining for queries
- **Optional**: Null safety

### 3. Java Features
- **Streams**: Functional filtering
- **Lambda**: Predicates for conditions
- **Method References**: Clean code

### 4. Performance
- **Indexing**: O(1) vs O(n) lookups
- **Lazy Evaluation**: Build query, execute once
- **Space-Time Tradeoff**: Indexes use more space for speed

---

## Sample Usage

### Basic CRUD
```java
InMemoryCRUD<User> crud = new InMemoryCRUD<>();

// Create
User user = new User("Alice", "alice@test.com", 25);
crud.create(user);

// Read
Optional<User> found = crud.read("1");

// Update
crud.update("1", new User("Alice Smith", "alice@test.com", 26));

// Delete
crud.delete("1");

// Filter
List<User> adults = crud.filter(u -> u.getAge() >= 18);
```

### Database Queries
```java
SimpleDatabase db = new SimpleDatabase();
db.createTable("users");

// Insert
db.table("users").insert(Map.of(
    "name", "Alice",
    "age", 25,
    "city", "NYC"
));

// Query
List<Record> results = db.table("users")
    .select()
    .where("city", "NYC")
    .whereGreaterThan("age", 20)
    .orderBy("name")
    .limit(10)
    .execute();
```

---

## How to Run

```bash
# Basic CRUD
javac crud/InMemoryCRUD.java
java crud.InMemoryCRUD

# Database with queries
javac crud/SimpleDatabase.java
java crud.SimpleDatabase
```

---

## Interview Tips

1. **Start Simple**: Begin with basic HashMap CRUD
2. **Add Features**: Filtering, validation, Optional
3. **Optimize**: Add indexes if asked about performance
4. **Discuss Tradeoffs**: Memory vs speed, simplicity vs features
5. **Edge Cases**: Null handling, duplicates, concurrent access

**Time to Code:**
- Basic CRUD: 10-15 minutes
- Query Builder: 20-25 minutes
- With Indexes: 30 minutes
