import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();
        String str = sc.nextLine();

        char[] result = str.toCharArray();
        int sum = 0;
        for (int i = 0; i < result.length; i++) {
            sum += result[i] - '0';
        }

        System.out.print(sum);
    }
}