import java.util.*;

public class KuhnMatching {
    static int left, right;
    static int[][] adj;
    static int[] size, matchRight;
    static boolean[] seen;

    static boolean augment(int u) {
        for (int i = 0; i < size[u]; i++) {
            int v = adj[u][i];
            if (seen[v]) continue;
            seen[v] = true;

            if (matchRight[v] == -1 || augment(matchRight[v])) {
                matchRight[v] = u;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        left = sc.nextInt();
        right = sc.nextInt();
        adj = new int[left][right];
        size = new int[left];

        for (int u = 0; u < left; u++) {
            size[u] = sc.nextInt();
            for (int j = 0; j < size[u]; j++) adj[u][j] = sc.nextInt() - 1;
        }

        matchRight = new int[right];
        Arrays.fill(matchRight, -1);
        int count = 0;

        for (int u = 0; u < left; u++) {
            seen = new boolean[right];
            if (augment(u)) count++;
        }

        System.out.println("Maximum Matching: " + count);
        for (int v = 0; v < right; v++)
            if (matchRight[v] != -1)
                System.out.println("Left " + (matchRight[v] + 1) + " - Right " + (v + 1));
        sc.close();
    }
}
