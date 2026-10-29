import java.util.Scanner;

public class GraphColoring {
    static int[][] graph;
    static int[] color;
    static int V, chromatic, largest;

    static boolean isSafe(int vertex, int c) {
        for (int i = 0; i < V; i++)
            if (graph[vertex][i] == 1 && color[i] == c) return false;
        return true;
    }

    static boolean solve(int vertex, int count) {
        if (vertex == V) {
            chromatic = count;
            int[] group = new int[count + 1];
            for (int i = 0; i < V; i++) group[color[i]]++;
            largest = 0;
            for (int i = 1; i <= count; i++) largest = Math.max(largest, group[i]);
            return true;
        }
        for (int c = 1; c <= count; c++) {
            if (isSafe(vertex, c)) {
                color[vertex] = c;
                if (solve(vertex + 1, count)) return true;
                color[vertex] = 0;
            }
        }
        return false;
    }

    static void execute(Scanner sc) {
        V = sc.nextInt();
        graph = new int[V][V];
        color = new int[V];
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++) graph[i][j] = sc.nextInt();
        for (int count = 1; count <= V; count++) {
            for (int i = 0; i < V; i++) color[i] = 0;
            if (solve(0, count)) break;
        }
        System.out.println("Chromatic Number: " + chromatic);
        System.out.println("Largest Group Size: " + largest);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        execute(sc);
    }
}
