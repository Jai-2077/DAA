import java.util.Scanner;

public class BoundedSubsetGenerator {
    static int[] arr, current;
    static int n, limit, size;

    static void generate(int index) {
        System.out.print("[");
        for (int i = 0; i < size; i++)
            System.out.print((i == 0 ? "" : ", ") + current[i]);
        System.out.println("]");

        for (int i = index; i < n; i++) {
            if (size < limit) {
                current[size++] = arr[i];
                generate(i + 1);
                size--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        limit = sc.nextInt();
        arr = new int[n];
        current = new int[Math.max(0, Math.min(n, limit))];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        generate(0);
        sc.close();
    }
}
