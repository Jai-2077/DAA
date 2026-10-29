import java.util.*;

public class Dijkstra {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] graph = new int[n][n];

        // Enter an adjacency matrix; use 0 for no edge. Weights must be non-negative.
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                graph[i][j] = sc.nextInt();

        int source = sc.nextInt(); // zero-based source vertex
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            for (int v = 0; v < n; v++)
                if (!visited[v] && (u == -1 || dist[v] < dist[u])) u = v;

            if (u == -1 || dist[u] == INF) break;
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                    dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        for (int i = 0; i < n; i++)
            System.out.println("Distance to " + i + ": " + (dist[i] == INF ? "INF" : dist[i]));
        sc.close();
    }
}
