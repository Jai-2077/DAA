import java.util.*;

public class SubsetSum {
    static int[] a, chosen;
    static int n, target, count;

    static void search(int i, int sum, int size) {
        if (sum == target) {
            count++;
            System.out.print("[");
            for (int j = 0; j < size; j++)
                System.out.print((j == 0 ? "" : ", ") + chosen[j]);
            System.out.println("]");
            // Continue searching to allow zero-valued elements in other valid subsets.
        }
        if (i == n) return;

        chosen[size] = a[i];
        search(i + 1, sum + a[i], size + 1);
        search(i + 1, sum, size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = new int[n];
        chosen = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        target = sc.nextInt();
        search(0, 0, 0);
        System.out.println("Number of matching subsets: " + count);
        sc.close();
    }
}
