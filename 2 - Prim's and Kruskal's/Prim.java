import java.util.Scanner;
import java.util.Arrays;

public class Prim {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] graph = new int[n][n];

        // Enter an adjacency matrix; use 0 for no edge (except diagonal).
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                graph[i][j] = sc.nextInt();

        int[] key = new int[n];
        int[] parent = new int[n];
        boolean[] used = new boolean[n];
        Arrays.fill(key, INF);
        Arrays.fill(parent, -1);
        key[0] = 0;

        int total = 0;
        for (int count = 0; count < n; count++) {
            int u = -1;
            for (int v = 0; v < n; v++)
                if (!used[v] && (u == -1 || key[v] < key[u])) u = v;

            if (u == -1 || key[u] == INF) {
                System.out.println("Graph is disconnected; MST does not exist.");
                sc.close();
                return;
            }

            used[u] = true;
            if (parent[u] != -1) {
                System.out.println(parent[u] + " - " + u + " : " + graph[parent[u]][u]);
                total += graph[parent[u]][u];
            }

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !used[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }

        System.out.println("MST cost: " + total);
        sc.close();
    }
}
