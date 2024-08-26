import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {

            int num = sc.nextInt();
            if (num != 0) {
                stack.push(num);
            } else {
                stack.pop();
            }
        }
        int sum =0;
        for (Integer i : stack) {
            sum += i;
        }
        System.out.println(sum);
    }
}
