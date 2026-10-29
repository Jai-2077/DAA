import java.util.Scanner;

public class ProfitAssignmentSolver {
    static int best;
    static int[][] profit;
    static int n;
    static boolean[] used;

    static void search(int row, int current) {
        if (row == n) {
            if (current > best) best = current;
            return;
        }
        int bound = current;
        for (int i = row; i < n; i++) {
            int max = 0;
            for (int j = 0; j < n; j++)
                if (!used[j] && profit[i][j] > max) max = profit[i][j];
            bound += max;
        }
        if (bound <= best) return;
        for (int j = 0; j < n; j++) {
            if (!used[j]) {
                used[j] = true;
                search(row + 1, current + profit[row][j]);
                used[j] = false;
            }
        }
    }

    public static int solve(int[][] matrix, int size) {
        n = size; profit = matrix; used = new boolean[n]; best = 0;
        search(0, 0);
        return best;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] profit = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) profit[i][j] = sc.nextInt();
        System.out.println(solve(profit, n));
    }
}
