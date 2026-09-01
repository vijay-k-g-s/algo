package greedy;

// Problem (LC 134): There are n gas stations in a circle. gas[i] = gas at station i.
//          cost[i] = gas to travel from i to i+1. Starting with an empty tank,
//          find the starting station index to complete the circuit, or -1 if impossible.
// Example: gas = [1,2,3,4,5], cost = [3,4,5,1,2] → 3  (start at index 3)
//          gas = [2,3,4], cost = [3,4,3] → -1
// Approach: Greedy.
//   If total gas >= total cost, a solution always exists.
//   Track running tank. If it drops below 0, the current start can't work.
//   Reset start to next station and reset tank to 0.
//   The valid start at the end is the answer.
// Time: O(n), Space: O(1)
//
// ─────────────────────────────────────────────────────────────────────────────

public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0, tank = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i] - cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        return totalGas >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        GasStation sol = new GasStation();
        System.out.println(sol.canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2})); // 3
        System.out.println(sol.canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));         // -1
    }
}
