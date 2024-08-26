import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        Queue<Integer> list = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            list.offer(i);
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            result = list.poll();
            if (list.isEmpty()) {
                break;
            }
            list.offer(list.poll());
        }

        System.out.println(result);

    }
}