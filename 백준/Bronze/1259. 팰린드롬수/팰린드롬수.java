import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {


            String str = sc.nextLine();
            if (str.charAt(0) == '0') {
                break;
            }

            boolean isTrue = true;
            int[] array = new int[str.length()];
            for (int i = 0; i < str.length(); i++) {
                array[i] = str.charAt(i) - '0';
            }
            int[] array2 = new int[str.length()];

            for (int i = array.length - 1, j = 0; i >= 0; i--, j++) {
                array2[j] = array[i];
            }

            for (int i = 0; i < array.length; i++) {
                if (array[i] != array2[i]) {
                    isTrue = false;
                }
            }

            if (isTrue) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }


        }
    }
}