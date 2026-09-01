package graph;

// LeetCode 721. Accounts Merge
// Approach: Union-Find (DSU) keyed on emails.
//   - Assign each unique email a numeric index.
//   - For every account, union all of its emails under the first email's index.
//   - After processing all accounts, group emails by their DSU root.
//   - Sort each group, prepend the owner name, and collect results.
//
// Key insight: two accounts belong to the same person iff they share at least one
// email. DSU propagates this transitively across any chain of shared emails.
//
// Complexity:
//   Time:  O(N α(N) + N log N)  — N = total emails; α = inverse Ackermann (sort dominates)
//   Space: O(N)

import java.util.*;

public class AccountsMerge {

    private int[] parent;
    private int[] rank;

    private void init(int n) {
        parent = new int[n];
        rank   = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    private int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    private void union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return;
        if      (rank[px] < rank[py]) parent[px] = py;
        else if (rank[px] > rank[py]) parent[py] = px;
        else { parent[py] = px; rank[px]++; }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailIdx   = new HashMap<>();
        Map<String, String>  emailOwner = new HashMap<>();
        int idx = 0;

        // Assign an index to every unique email; remember its owner name
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (!emailIdx.containsKey(email)) emailIdx.put(email, idx++);
                emailOwner.put(email, name);
            }
        }

        init(idx);

        // Union all emails within the same account under the first email
        for (List<String> account : accounts) {
            int first = emailIdx.get(account.get(1));
            for (int i = 2; i < account.size(); i++) {
                union(first, emailIdx.get(account.get(i)));
            }
        }

        // Group emails by DSU root
        Map<Integer, List<String>> groups = new HashMap<>();
        for (String email : emailIdx.keySet()) {
            int root = find(emailIdx.get(email));
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
        }

        // Build result: sort emails, prepend owner name
        List<List<String>> result = new ArrayList<>();
        for (List<String> emails : groups.values()) {
            Collections.sort(emails);
            List<String> merged = new ArrayList<>();
            merged.add(emailOwner.get(emails.get(0)));
            merged.addAll(emails);
            result.add(merged);
        }

        return result;
    }

    public static void main(String[] args) {
        AccountsMerge solution = new AccountsMerge();

        // Test 1 — two John accounts share "johnsmith@mail.com" and must merge
        List<List<String>> accounts1 = new ArrayList<>();
        accounts1.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts1.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts1.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts1.add(Arrays.asList("John", "johnnybravo@mail.com"));
        System.out.println(solution.accountsMerge(accounts1));
        // Expected (order of top-level lists may vary):
        // [John, john00@mail.com, john_newyork@mail.com, johnsmith@mail.com]
        // [Mary, mary@mail.com]
        // [John, johnnybravo@mail.com]

        // Test 2 — no shared emails; each account stays independent
        List<List<String>> accounts2 = new ArrayList<>();
        accounts2.add(Arrays.asList("Gabe",  "Gabe0@m.co",  "Gabe3@m.co",  "Gabe1@m.co"));
        accounts2.add(Arrays.asList("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"));
        accounts2.add(Arrays.asList("Ethan", "Ethan5@m.co", "Ethan4@m.co", "Ethan0@m.co"));
        accounts2.add(Arrays.asList("Hanzo", "Hanzo3@m.co", "Hanzo1@m.co", "Hanzo0@m.co"));
        accounts2.add(Arrays.asList("Fern",  "Fern5@m.co",  "Fern1@m.co",  "Fern0@m.co"));
        System.out.println(solution.accountsMerge(accounts2));
        // Expected: 5 separate accounts, each with emails sorted alphabetically
    }
}
