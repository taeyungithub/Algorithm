import java.util.Scanner;

public class RationalOperators {

    public static int[] plus(int[] left, int[] right) {
        int numerator = left[0] * right[1] + left[1] * right[0];
        int denominator = left[1] * right[1];

        int g = gCD(numerator, denominator);
        return new int[] { numerator / g, denominator / g };
    }

    public static int[] subtract(int[] left, int[] right) {
        int numerator = left[0] * right[1] - left[1] * right[0];
        int denominator = left[1] * right[1];

        int g = gCD(numerator, denominator);

        return new int[] { numerator / g, denominator / g };
    }

    public static int[] multiply(int[] left, int[] right) {
        int numerator = left[0] * right[0];
        int denominator = left[1] * right[1];

        int g = gCD(numerator, denominator);

        return new int[] { numerator / g, denominator / g };
    }

    public static int[] divide(int[] left, int[] right) {
        int temp = 0;
        temp = right[0];
        right[0] = right[1];
        right[1] = temp;

        return multiply(left, right);
    }

    public static int gCD(int a, int b) {

        // 유클리드 알고리즘
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (a % b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return b;
    }

    public static void print(int[] array) {
        System.out.println("result: ");
        System.out.println(" " + array[0]);
        System.out.println("---");
        System.out.println(" " + array[1]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] left = new int[2];
        int[] right = new int[2];

        for (int i = 0; i < 2; i++) {
            left[i] = sc.nextInt();
        }

        sc.nextLine();
        String str = sc.nextLine();

        for (int i = 0; i < 2; i++) {
            right[i] = sc.nextInt();
        }
        sc.close();

        int[] result = new int[2];

        switch (str) {
            case "+":
                result = plus(left, right);
                break;
            case "-":
                result = subtract(left, right);
                break;
            case "*":
                result = multiply(left, right);
                break;
            case "/":
                result = divide(left, right);
                break;
            default:
                break;
        }

        print(result);
    }
}