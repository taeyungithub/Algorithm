import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {

            int H = sc.nextInt();
            int W = sc.nextInt();
            int N = sc.nextInt();

            int X, Y;

            if (N % H == 0) {
                Y = H * 100;
                X = N / H;
            }
            else {
                Y = (N % H) * 100;
                X = (N / H) + 1;
            }

            int result = Y + X;
            System.out.println(result);
        }
    }
}