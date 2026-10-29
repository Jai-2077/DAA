import java.util.Scanner;

public class CountingAssignment {
    static int n, bestCost, ways;
    static int[][] cost;
    static boolean[] used;

    static int lowerBound(int row, int currentCost) {
        int bound = currentCost;
        for (int i = row; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++)
                if (!used[j]) min = Math.min(min, cost[i][j]);
            bound += min;
        }
        return bound;
    }

    static void solve(int row, int currentCost) {
        if (row == n) {
            if (currentCost < bestCost) { bestCost = currentCost; ways = 1; }
            else if (currentCost == bestCost) ways++;
            return;
        }
        if (lowerBound(row, currentCost) > bestCost) return;
        for (int j = 0; j < n; j++) {
            if (!used[j]) {
                used[j] = true;
                solve(row + 1, currentCost + cost[row][j]);
                used[j] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cost = new int[n][n];
        used = new boolean[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) cost[i][j] = sc.nextInt();
        bestCost = Integer.MAX_VALUE;
        solve(0, 0);
        System.out.println("Minimum Cost: " + bestCost);
        System.out.println("Optimal Assignments: " + ways);
    }
}
