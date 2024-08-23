import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] result = new int[9];

        for (int i = 0; i < 9; i++) {
            result[i] = sc.nextInt();
        }
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < 9; i++) {
            if (result[i] > max) {
                max = result[i];
                maxIndex = i + 1;
            }
        }
        System.out.println(max + "\n" + maxIndex);
    }
}