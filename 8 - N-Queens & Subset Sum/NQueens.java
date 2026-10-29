import java.util.Scanner;

public class NQueens {
    static int n, count;
    static int[] col, first;

    static boolean safe(int row, int c) {
        for (int r = 0; r < row; r++)
            if (col[r] == c || Math.abs(col[r] - c) == row - r) return false;
        return true;
    }

    static void solve(int row) {
        if (row == n) {
            count++;
            if (first == null) first = col.clone();
            return;
        }
        for (int c = 0; c < n; c++) {
            if (safe(row, c)) {
                col[row] = c;
                solve(row + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        col = new int[n];
        solve(0);

        if (first == null) {
            System.out.println("No solution found.");
        } else {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++)
                    System.out.print((first[r] == c ? 1 : 0) + (c + 1 == n ? "" : " "));
                System.out.println();
            }
            System.out.println("Total Solutions: " + count);
        }
        sc.close();
    }
}
