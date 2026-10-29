import java.util.Scanner;

public class Warshall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] reach = new int[n][n];

        // Enter a 0/1 adjacency matrix.
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                reach[i][j] = sc.nextInt();

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (reach[i][k] == 1 && reach[k][j] == 1)
                        reach[i][j] = 1;
                }
            }
        }

        System.out.println("Transitive closure:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(reach[i][j] + (j + 1 == n ? "" : " "));
            System.out.println();
        }
        sc.close();
    }
}
