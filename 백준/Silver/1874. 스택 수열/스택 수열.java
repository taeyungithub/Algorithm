
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int temp = 1;
        boolean err = false;


        Stack<Integer> stack = new Stack<>();
        int N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            int item = sc.nextInt();

            for (; temp <= item; temp++) {
                stack.push(temp);
                sb.append("+").append("\n");
            }
            if (stack.peek() == item) {
                stack.pop();
                sb.append("-").append("\n");
            } else {
                err = true;
            }

        }

        if (err) {
            System.out.println("NO");
        } else {
            System.out.println(sb);

        }
    }
}
