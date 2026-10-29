import java.util.Scanner;

public class OptimalBST {
    static int[][] root;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] keys = new int[n];
        int[] freq = new int[n];

        for (int i = 0; i < n; i++) keys[i] = sc.nextInt();
        for (int i = 0; i < n; i++) freq[i] = sc.nextInt();

        int[][] dp = new int[n][n];
        root = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = freq[i];
            root[i][i] = i;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                int sumFreq = 0;
                for (int k = i; k <= j; k++) sumFreq += freq[k];

                dp[i][j] = Integer.MAX_VALUE;
                for (int r = i; r <= j; r++) {
                    int left = (r > i) ? dp[i][r - 1] : 0;
                    int right = (r < j) ? dp[r + 1][j] : 0;
                    int candidate = left + right + sumFreq;

                    if (candidate < dp[i][j]) {
                        dp[i][j] = candidate;
                        root[i][j] = r;
                    }
                }
            }
        }

        System.out.println("Minimum cost= " + (n == 0 ? 0 : dp[0][n - 1]));
        System.out.println("Preorder traversal:");
        if (n > 0) printPreorder(0, n - 1, keys);
        System.out.println();
        sc.close();
    }

    static void printPreorder(int i, int j, int[] keys) {
        if (i > j) return;
        int r = root[i][j];
        System.out.print(keys[r] + " ");
        printPreorder(i, r - 1, keys);
        printPreorder(r + 1, j, keys);
    }
}
