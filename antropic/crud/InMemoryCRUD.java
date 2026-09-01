package antropic.crud;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-Memory CRUD Engine - Interview Version (15 min coding)
 *
 * Core Operations:
 * - Create: Add new entity
 * - Read: Get by ID, get all, filter
 * - Update: Modify existing entity
 * - Delete: Remove entity
 *
 * Data Structure: HashMap for O(1) operations
 * Interview Topics: HashMap, Optional, Generics, Functional Interface
 */
public class InMemoryCRUD<T extends Entity> {

    private final Map<String, T> storage;
    private int nextId;

    public InMemoryCRUD() {
        this.storage = new HashMap<>();
        this.nextId = 1;
    }

    /**
     * CREATE: Add new entity
     * @param entity - entity to add
     * @return the created entity with generated ID
     */
    public T create(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }

        // Generate ID if not set
        if (entity.getId() == null || entity.getId().isEmpty()) {
            entity.setId(String.valueOf(nextId++));
        }

        if (storage.containsKey(entity.getId())) {
            throw new IllegalArgumentException("Entity with ID " + entity.getId() + " already exists");
        }

        storage.put(entity.getId(), entity);
        return entity;
    }

    /**
     * READ: Get entity by ID
     * @param id - entity ID
     * @return Optional containing entity if found
     */
    public Optional<T> read(String id) {
        if (id == null || id.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(storage.get(id));
    }

    /**
     * READ: Get all entities
     * @return List of all entities
     */
    public List<T> readAll() {
        return new ArrayList<>(storage.values());
    }

    /**
     * READ: Filter entities by condition
     * @param filter - predicate to filter entities
     * @return List of matching entities
     */
    public List<T> filter(java.util.function.Predicate<T> filter) {
        return storage.values().stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    /**
     * UPDATE: Modify existing entity
     * @param id - entity ID to update
     * @param entity - new entity data
     * @return Optional containing updated entity if found
     */
    public Optional<T> update(String id, T entity) {
        if (id == null || id.isEmpty() || entity == null) {
            return Optional.empty();
        }

        if (!storage.containsKey(id)) {
            return Optional.empty();
        }

        entity.setId(id); // Ensure ID doesn't change
        storage.put(id, entity);
        return Optional.of(entity);
    }

    /**
     * DELETE: Remove entity by ID
     * @param id - entity ID to delete
     * @return true if deleted, false if not found
     */
    public boolean delete(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return storage.remove(id) != null;
    }

    /**
     * Check if entity exists
     * @param id - entity ID
     * @return true if exists
     */
    public boolean exists(String id) {
        return storage.containsKey(id);
    }

    /**
     * Get total count
     * @return number of entities
     */
    public int count() {
        return storage.size();
    }

    /**
     * Clear all entities
     */
    public void clear() {
        storage.clear();
        nextId = 1;
    }

    // ============== TEST WITH USER ENTITY ==============

    public static void main(String[] args) {
        System.out.println("=== In-Memory CRUD Engine Tests ===\n");

        testBasicCRUD();
        testFiltering();
        testEdgeCases();
        testBulkOperations();
    }

    private static void testBasicCRUD() {
        System.out.println("Test 1: Basic CRUD Operations");
        InMemoryCRUD<User> crud = new InMemoryCRUD<>();

        // CREATE
        User user1 = new User("Alice", "alice@test.com", 25);
        User user2 = new User("Bob", "bob@test.com", 30);
        crud.create(user1);
        crud.create(user2);
        System.out.println("Created: " + user1);
        System.out.println("Created: " + user2);

        // READ
        Optional<User> found = crud.read("1");
        System.out.println("Read ID=1: " + found.orElse(null));

        // UPDATE
        User updated = new User("Alice Smith", "alice.smith@test.com", 26);
        crud.update("1", updated);
        System.out.println("Updated ID=1: " + crud.read("1").orElse(null));

        // DELETE
        boolean deleted = crud.delete("2");
        System.out.println("Deleted ID=2: " + deleted);
        System.out.println("Count: " + crud.count());
        System.out.println();
    }

    private static void testFiltering() {
        System.out.println("Test 2: Filtering");
        InMemoryCRUD<User> crud = new InMemoryCRUD<>();

        crud.create(new User("Alice", "alice@test.com", 25));
        crud.create(new User("Bob", "bob@test.com", 30));
        crud.create(new User("Charlie", "charlie@test.com", 22));
        crud.create(new User("David", "david@test.com", 35));

        // Filter by age > 25
        List<User> adults = crud.filter(user -> user.getAge() > 25);
        System.out.println("Users with age > 25:");
        adults.forEach(System.out::println);

        // Filter by email domain
        List<User> testUsers = crud.filter(user -> user.getEmail().endsWith("@test.com"));
        System.out.println("\nUsers with @test.com email: " + testUsers.size());

        // Filter by name starting with 'C'
        List<User> cNames = crud.filter(user -> user.getName().startsWith("C"));
        System.out.println("Users with name starting with C:");
        cNames.forEach(System.out::println);
        System.out.println();
    }

    private static void testEdgeCases() {
        System.out.println("Test 3: Edge Cases");
        InMemoryCRUD<User> crud = new InMemoryCRUD<>();

        // Null entity
        try {
            crud.create(null);
            System.out.println("✗ Should throw exception for null entity");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Null entity rejected: " + e.getMessage());
        }

        // Duplicate ID
        User user1 = new User("Alice", "alice@test.com", 25);
        user1.setId("100");
        crud.create(user1);
        try {
            User user2 = new User("Bob", "bob@test.com", 30);
            user2.setId("100");
            crud.create(user2);
            System.out.println("✗ Should throw exception for duplicate ID");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Duplicate ID rejected: " + e.getMessage());
        }

        // Update non-existent
        Optional<User> updated = crud.update("999", new User("Ghost", "ghost@test.com", 0));
        System.out.println("✓ Update non-existent: " + (updated.isEmpty() ? "Returns empty" : "✗ Error"));

        // Delete non-existent
        boolean deleted = crud.delete("999");
        System.out.println("✓ Delete non-existent: " + (!deleted ? "Returns false" : "✗ Error"));
        System.out.println();
    }

    private static void testBulkOperations() {
        System.out.println("Test 4: Bulk Operations");
        InMemoryCRUD<User> crud = new InMemoryCRUD<>();

        // Bulk create
        for (int i = 0; i < 100; i++) {
            crud.create(new User("User" + i, "user" + i + "@test.com", 20 + (i % 40)));
        }
        System.out.println("Created 100 users");
        System.out.println("Total count: " + crud.count());

        // Bulk filter
        List<User> seniors = crud.filter(user -> user.getAge() >= 50);
        System.out.println("Users aged >= 50: " + seniors.size());

        // Get all
        List<User> all = crud.readAll();
        System.out.println("Read all: " + all.size() + " users");

        // Clear
        crud.clear();
        System.out.println("After clear: " + crud.count() + " users");
    }
}

/**
 * Base interface for entities (must have ID)
 */
interface Entity {
    String getId();
    void setId(String id);
}

/**
 * Sample User entity for testing
 */
class User implements Entity {
    private String id;
    private String name;
    private String email;
    private int age;

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "', age=" + age + "}";
    }
}
