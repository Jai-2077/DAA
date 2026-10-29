import java.util.Scanner;

public class MatrixChainMultiplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of matrices
        int[] p = new int[n + 1];

        // Matrix i has dimensions p[i-1] x p[i].
        for (int i = 0; i <= n; i++) p[i] = sc.nextInt();

        int[][] dp = new int[n + 1][n + 1];

        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        System.out.println("Minimum multiplication cost: " + dp[1][n]);
        sc.close();
    }
}
