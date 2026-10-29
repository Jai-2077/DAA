import java.util.Scanner;

class Edge {
    int u, v;
    Edge(int u, int v) { this.u = u; this.v = v; }
}

public class EdgeColoring {
    static Edge[] edges;
    static int[] color;
    static int V, E;

    static boolean isSafe(int index, int c) {
        Edge e = edges[index];
        for (int i = 0; i < index; i++) {
            if (color[i] == c &&
                (edges[i].u == e.u || edges[i].u == e.v ||
                 edges[i].v == e.u || edges[i].v == e.v)) return false;
        }
        return true;
    }

    static boolean solve(int index, int limit) {
        if (index == E) return true;
        for (int c = 1; c <= limit; c++) {
            if (isSafe(index, c)) {
                color[index] = c;
                if (solve(index + 1, limit)) return true;
                color[index] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        edges = new Edge[E];
        color = new int[E];
        for (int i = 0; i < E; i++) edges[i] = new Edge(sc.nextInt(), sc.nextInt());
        for (int limit = 1; limit <= E; limit++) {
            if (solve(0, limit)) {
                System.out.println("Edge Chromatic Number: " + limit);
                for (int i = 0; i < E; i++)
                    System.out.println(edges[i].u + " " + edges[i].v + " : " + color[i]);
                return;
            }
        }
    }
}
