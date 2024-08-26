import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String list[] = new String[N];
        for (int i = 0; i < N; i++)
            list[i] = br.readLine();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (list[i].length() == list[j].length()) {
                    if (list[i].compareTo(list[j]) > 0) {
                        String tmp = list[j];
                        list[j] = list[i];
                        list[i] = tmp;
                    }
                } else {
                    if (list[i].length() > list[j].length()) {
                        String tmp = list[j];
                        list[j] = list[i];
                        list[i] = tmp;
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (i != 0) {
                if (list[i].compareTo(list[i - 1]) != 0)
                    System.out.println(list[i]);
            } else
                System.out.println(list[i]);
        }
    }
}