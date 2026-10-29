import java.util.*;

public class MaxFlow {
    static boolean bfs(int[][] residual, int s, int t, int[] parent) {
        boolean[] seen = new boolean[residual.length];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        seen[s] = true;
        parent[s] = -1;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < residual.length; v++) {
                if (!seen[v] && residual[u][v] > 0) {
                    parent[v] = u;
                    seen[v] = true;
                    if (v == t) return true;
                    q.add(v);
                }
            }
        }
        return false;
    }

    static int maxFlow(int[][] capacity, int s, int t) {
        int n = capacity.length, flow = 0;
        int[][] residual = new int[n][n];
        for (int i = 0; i < n; i++) residual[i] = capacity[i].clone();
        int[] parent = new int[n];

        while (bfs(residual, s, t, parent)) {
            int add = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v])
                add = Math.min(add, residual[parent[v]][v]);

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= add;
                residual[v][u] += add;
            }
            flow += add;
        }
        return flow;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] capacity = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) capacity[i][j] = sc.nextInt();
        int source = sc.nextInt(), sink = sc.nextInt(); // 0-based vertices
        System.out.println("Maximum Flow: " + maxFlow(capacity, source, sink));
        sc.close();
    }
}
