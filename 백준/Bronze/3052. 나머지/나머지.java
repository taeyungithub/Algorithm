import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] result = new int[42];


        for (int i = 0; i < 10; i++) {
            int n = sc.nextInt() % 42;
            result[n] = result[n] + 1;
        }
        int count =0;

        for (int i : result) {
            if (i > 0) {
                count ++;
            }
        }
        System.out.println(count);
    }
}