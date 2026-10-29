import java.util.Scanner;

public class FloydWarshall {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] dist = new int[n][n];

        // Enter weighted adjacency matrix; use 999999 to represent no edge.
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int x = sc.nextInt();
                dist[i][j] = (x >= 999999) ? INF : x;
            }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        System.out.println("All-pairs shortest distances:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((dist[i][j] == INF ? "INF" : dist[i][j]) +
                                 (j + 1 == n ? "" : " "));
            }
            System.out.println();
        }
        sc.close();
    }
}
