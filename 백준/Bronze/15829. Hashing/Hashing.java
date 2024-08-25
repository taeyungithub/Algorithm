import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); sc.nextLine();
        String str = sc.nextLine();
        char[] array = str.toCharArray();

        long sum =0;
        for (int i = 0; i < n; i++) {
            int num = array[i] - 96;

            sum += (num * Math.pow(31, i));

        }
        System.out.println(sum);
    }
}