import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        int[][] kn = new int[15][15];

        for (int i = 0; i < 15; i++) {
            kn[i][1] = 1;
            kn[0][i] = i;
        }
        for (int i = 1; i < 15; i++) {
            for (int j = 2; j < 15; j++) {
                kn[i][j] = kn[i][j - 1] + kn[i - 1][j];
            }
        }

        for (int i = 0; i < t; i++) {
            int k = sc.nextInt();
            int n = sc.nextInt();

            System.out.println(kn[k][n]);
        }
    }
}