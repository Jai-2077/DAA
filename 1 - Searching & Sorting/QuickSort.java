import java.util.Scanner;

public class QuickSort {
    static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        int pi = i + 1;
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        if (n > 0) quickSort(arr, 0, n - 1);

        System.out.print("Sorted colors:");
        for (int x : arr) System.out.print(" " + x);
        System.out.println();
        sc.close();
    }
}
