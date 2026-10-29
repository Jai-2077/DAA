import java.util.Scanner;

public class Puzzle15Compare {
    static final int N = 4;
    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};
    static final int[] BACK = {1, 0, 3, 2};
    static char[] board, target;
    static int blank, limit, answer;

    static int heuristic() {
        int count = 0;
        for (int i = 0; i < 16; i++)
            if (board[i] != '0' && board[i] != target[i]) count++;
        return count;
    }

    static boolean search(int zero, int depth, int previousMove) {
        int h = heuristic();
        if (depth + h > limit) return false;
        if (h == 0) { answer = depth; return true; }
        int r = zero / N, c = zero % N;
        for (int move = 0; move < 4; move++) {
            if (move == previousMove) continue;
            int nr = r + DR[move], nc = c + DC[move];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            int next = nr * N + nc;
            char temp = board[zero]; board[zero] = board[next]; board[next] = temp;
            if (search(next, depth + 1, BACK[move])) return true;
            temp = board[zero]; board[zero] = board[next]; board[next] = temp;
        }
        return false;
    }

    static int distance(int[] start, int[] end) {
        board = new char[16]; target = new char[16];
        int zero = -1;
        for (int i = 0; i < 16; i++) {
            board[i] = (char) ('0' + start[i]);
            target[i] = (char) ('0' + end[i]);
            if (start[i] == 0) zero = i;
        }
        if (!sameParity(start, end)) return -1;
        limit = heuristic();
        while (limit <= 80) {
            if (search(zero, 0, -1)) return answer;
            limit++;
        }
        return -1;
    }

    static boolean sameParity(int[] a, int[] b) {
        return parity(a) == parity(b);
    }

    static int parity(int[] a) {
        int inversions = 0, rowFromBottom = 4;
        for (int i = 0; i < 16; i++) {
            if (a[i] == 0) { rowFromBottom = 4 - i / 4; continue; }
            for (int j = i + 1; j < 16; j++)
                if (a[j] != 0 && a[i] > a[j]) inversions++;
        }
        return (inversions + rowFromBottom) % 2;
    }

    static int[] readBoard(Scanner sc) {
        int[] a = new int[16];
        for (int i = 0; i < 16; i++) a[i] = sc.nextInt();
        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] mainBoard = readBoard(sc);
        int[] board1 = readBoard(sc);
        int[] board2 = readBoard(sc);
        int d1 = distance(mainBoard, board1);
        int d2 = distance(mainBoard, board2);
        System.out.println("Moves to Board 1: " + d1);
        System.out.println("Moves to Board 2: " + d2);
        if (d1 == -1 && d2 == -1) System.out.println("Neither board is reachable");
        else if (d1 == -1) System.out.println("Board 2 is closer");
        else if (d2 == -1) System.out.println("Board 1 is closer");
        else if (d1 < d2) System.out.println("Board 1 is closer");
        else if (d2 < d1) System.out.println("Board 2 is closer");
        else System.out.println("Both boards are equally close");
    }
}
