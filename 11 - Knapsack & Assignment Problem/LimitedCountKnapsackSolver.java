import java.util.Scanner;

public class LimitedCountKnapsackSolver {
    static int maxProfit;

    static int solve(int[] weight, int[] value, int capacity, int maxItems, int n) {
        maxProfit = 0;
        int[] idx = new int[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        for (int i = 0; i < n - 1; i++) {
            int best = i;
            for (int j = i + 1; j < n; j++)
                if ((double) value[idx[j]] / weight[idx[j]] > (double) value[idx[best]] / weight[idx[best]]) best = j;
            int temp = idx[i]; idx[i] = idx[best]; idx[best] = temp;
        }
        search(0, 0, 0, 0, capacity, maxItems, n, weight, value, idx);
        return maxProfit;
    }

    static void search(int i, int cw, int cp, int cc, int W, int K, int n,
                       int[] w, int[] v, int[] idx) {
        if (cc <= K && cp > maxProfit) maxProfit = cp;
        if (i == n || cc == K) return;
        double bound = cp;
        int remW = W - cw;
        for (int j = i; j < n && remW > 0; j++) {
            int id = idx[j];
            if (w[id] <= remW) { bound += v[id]; remW -= w[id]; }
            else { bound += (double) remW / w[id] * v[id]; break; }
        }
        if (bound <= maxProfit) return;
        int id = idx[i];
        if (cw + w[id] <= W)
            search(i + 1, cw + w[id], cp + v[id], cc + 1, W, K, n, w, v, idx);
        search(i + 1, cw, cp, cc, W, K, n, w, v, idx);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), capacity = sc.nextInt(), maxItems = sc.nextInt();
        int[] weight = new int[n], value = new int[n];
        for (int i = 0; i < n; i++) weight[i] = sc.nextInt();
        for (int i = 0; i < n; i++) value[i] = sc.nextInt();
        System.out.println(solve(weight, value, capacity, maxItems, n));
    }
}
