import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] result = new int[10];

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        String str = "" + (a * b * c);

        for (int i = 0; i < str.length(); i++) {
            int num = str.charAt(i) - '0';

            result[num] += 1;
        }

        for (int i : result) {
            System.out.println(i);
        }
    }
}