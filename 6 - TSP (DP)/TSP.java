import java.util.Arrays;
import java.util.Scanner;

public class TSP {
    static int n;
    static int[][] cost, memo;
    static final int INF = 1_000_000_000;

    static int tsp(int mask, int pos) {
        if (mask == (1 << n) - 1) return cost[pos][0];
        if (memo[mask][pos] != -1) return memo[mask][pos];

        int best = INF;
        for (int next = 0; next < n; next++) {
            if ((mask & (1 << next)) == 0) {
                int candidate = cost[pos][next] + tsp(mask | (1 << next), next);
                best = Math.min(best, candidate);
            }
        }
        return memo[mask][pos] = best;
    }

    static String reconstructPath() {
        int mask = 1, pos = 0;
        StringBuilder path = new StringBuilder("1");

        while (mask != (1 << n) - 1) {
            int target = tsp(mask, pos);
            int chosen = -1;

            for (int next = 0; next < n; next++) {
                if ((mask & (1 << next)) == 0) {
                    int candidate = cost[pos][next] + tsp(mask | (1 << next), next);
                    if (candidate == target) {
                        chosen = next;
                        break;
                    }
                }
            }

            if (chosen == -1) break;
            path.append(" ").append(chosen + 1);
            mask |= (1 << chosen);
            pos = chosen;
        }

        path.append(" 1");
        return path.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cost = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cost[i][j] = sc.nextInt();

        if (n == 0) {
            System.out.println("No cities.");
            sc.close();
            return;
        }

        memo = new int[1 << n][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        int minCost = tsp(1, 0);
        System.out.println("Shortest Path: " + reconstructPath());
        System.out.println("Minimum cost is " + minCost);
        sc.close();
    }
}
