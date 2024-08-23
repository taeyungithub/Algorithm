import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] result = new int[8];
        for (int i = 0; i < 8; i++) {
            result[i] = sc.nextInt();
        }
        int[] ascending = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] descending = {8, 7, 6, 5, 4, 3, 2, 1};

        if (Arrays.equals(result, ascending)) {
            System.out.println("ascending");
        } else if (Arrays.equals(result, descending)) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }
    }
}