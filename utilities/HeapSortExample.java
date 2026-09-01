package utilities;

// Utility: Demonstrates PriorityQueue with custom Comparators on objects.
//   PriorityQueue is a min-heap by default; supplying a Comparator changes the ordering.
//   Two Comparators shown:
//     AgeComparator  — orders Customer objects by age ascending.
//     NameComparator — orders Customer objects by name lexicographically.
// Key idiom: new PriorityQueue<>(new MyComparator())
//   or lambda: new PriorityQueue<>((a, b) -> a.age - b.age)
// Output: Sorted by age: Bob(25) Alice(30) Charlie(35)
//         Sorted by name: Alice Bob Charlie
//
// ─────────────────────────────────────────────────────────────────────────────

import java.util.*;

// Define Customer class with name and age attributes
class Customer {
    String name;
    int age;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class HeapSortExample {
    // Comparison function to sort customers by age
    static class AgeComparator implements Comparator<Customer> {
        public int compare(Customer c1, Customer c2) {
            return c1.age - c2.age;
        }
    }

    // Comparison function to sort customers by name
    static class NameComparator implements Comparator<Customer> {
        public int compare(Customer c1, Customer c2) {
            return c1.name.compareTo(c2.name);
        }
    }

    public static void main(String[] args) {
        // Sample customer objects
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Alice", 30));
        customers.add(new Customer("Bob", 25));
        customers.add(new Customer("Charlie", 35));

        // Sorting by age using heap
        PriorityQueue<Customer> agePriorityQueue = new PriorityQueue<>(new AgeComparator());
        agePriorityQueue.addAll(customers);
        System.out.println("Sorted by age:");
        while (!agePriorityQueue.isEmpty()) {
            Customer customer = agePriorityQueue.poll();
            System.out.println(customer.name + " " + customer.age);
        }

        // Sorting by name using heap
        PriorityQueue<Customer> namePriorityQueue = new PriorityQueue<>(new NameComparator());
        namePriorityQueue.addAll(customers);
        System.out.println("\nSorted by name:");
        while (!namePriorityQueue.isEmpty()) {
            Customer customer = namePriorityQueue.poll();
            System.out.println(customer.name + " " + customer.age);
        }
    }
}
