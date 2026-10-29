import java.util.*;

public class GaleShapley {
    static int[] stableMatch(int n, int[][] playerPref, int[][] teamPref) {
        int[] teamPartner = new int[n];
        int[] next = new int[n];
        Arrays.fill(teamPartner, -1);

        int[][] rank = new int[n][n];
        for (int t = 0; t < n; t++)
            for (int r = 0; r < n; r++)
                rank[t][teamPref[t][r]] = r;

        Queue<Integer> free = new ArrayDeque<>();
        for (int p = 0; p < n; p++) free.add(p);

        while (!free.isEmpty()) {
            int p = free.poll();
            int t = playerPref[p][next[p]++];

            if (teamPartner[t] == -1) {
                teamPartner[t] = p;
            } else {
                int old = teamPartner[t];
                if (rank[t][p] < rank[t][old]) {
                    teamPartner[t] = p;
                    free.add(old);
                } else {
                    free.add(p);
                }
            }
        }
        return teamPartner;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] playerPref = new int[n][n];
        int[][] teamPref = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) playerPref[i][j] = sc.nextInt() - 1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) teamPref[i][j] = sc.nextInt() - 1;

        int[] teamPartner = stableMatch(n, playerPref, teamPref);
        int[] playerPartner = new int[n];
        for (int t = 0; t < n; t++) playerPartner[teamPartner[t]] = t;

        int first = 0;
        for (int p = 0; p < n; p++) {
            System.out.println("Player " + (p + 1) + " - Team " + (playerPartner[p] + 1));
            if (playerPref[p][0] == playerPartner[p]) first++;
        }
        System.out.println("First-Choice Matches: " + first);
        sc.close();
    }
}
