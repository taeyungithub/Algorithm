import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = sc.nextInt();
        }

        int sum = 0;
        int result = 0;


        for (int i = 0; i < n - 2; i++) {
            for (int j = 1 + i; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    sum = array[i] + array[j] + array[k];
                    if (sum == m) {
                        System.out.println(sum);
                        return;
                    }
                    if (result < sum && sum < m) {
                        result = sum;
                    }
                }
            }
        }
        System.out.println(result);

    }
}