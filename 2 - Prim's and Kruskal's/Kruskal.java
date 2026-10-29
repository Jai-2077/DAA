import java.util.*;

public class Kruskal {
    static class Edge implements Comparable<Edge> {
        int u, v, weight;
        Edge(int u, int v, int weight) {
            this.u = u; this.v = v; this.weight = weight;
        }
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        boolean union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) return false;
            if (rank[ra] < rank[rb]) parent[ra] = rb;
            else if (rank[ra] > rank[rb]) parent[rb] = ra;
            else { parent[rb] = ra; rank[ra]++; }
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Edge> edges = new ArrayList<>();

        // Enter m edges as: u v weight. Vertices are numbered 0 to n-1.
        for (int i = 0; i < m; i++)
            edges.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));

        Collections.sort(edges);
        DSU dsu = new DSU(n);
        int total = 0, chosen = 0;

        for (Edge e : edges) {
            if (dsu.union(e.u, e.v)) {
                System.out.println(e.u + " - " + e.v + " : " + e.weight);
                total += e.weight;
                chosen++;
                if (chosen == n - 1) break;
            }
        }

        if (chosen != n - 1) System.out.println("Graph is disconnected; MST does not exist.");
        else System.out.println("MST cost: " + total);
        sc.close();
    }
}
