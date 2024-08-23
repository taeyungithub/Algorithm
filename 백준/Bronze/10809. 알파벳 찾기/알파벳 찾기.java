import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] result = new int[26];

        Arrays.fill(result, -1);

        String str = sc.next();

        for (int i = 0; i < str.length(); i++) {
            int k = str.charAt(i) - 97;
            if (result[k] == -1) {
                result[k] = i;
            }
        }
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}