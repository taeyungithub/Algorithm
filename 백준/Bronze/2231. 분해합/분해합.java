import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int result = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            int original = i;

            while (original != 0) {
                sum += original % 10;
                original = original / 10;
            }

            if (n == (i + sum)) {
                result = i;
                break;
            }
        }
        System.out.println(result);
    }
}