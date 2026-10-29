import java.util.*;

public class LCS {
    static String a, b;
    static int[][] dp;
    static Set<String>[][] memo;

    static Set<String> allLCS(int i, int j) {
        if (memo[i][j] != null) return memo[i][j];

        Set<String> ans = new TreeSet<>();

        if (i == 0 || j == 0) {
            ans.add("");
        } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
            for (String s : allLCS(i - 1, j - 1))
                ans.add(s + a.charAt(i - 1));
        } else {
            if (dp[i - 1][j] == dp[i][j])
                ans.addAll(allLCS(i - 1, j));
            if (dp[i][j - 1] == dp[i][j])
                ans.addAll(allLCS(i, j - 1));
        }

        memo[i][j] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.nextLine();
        b = sc.nextLine();

        int n = a.length(), m = b.length();
        dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        @SuppressWarnings("unchecked")
        Set<String>[][] temp = (Set<String>[][]) new Set[n + 1][m + 1];
        memo = temp;

        for (String s : allLCS(n, m))
            System.out.println(s.isEmpty() ? "(empty subsequence)" : s);
        System.out.println("LCS length: " + dp[n][m]);
        sc.close();
    }
}
