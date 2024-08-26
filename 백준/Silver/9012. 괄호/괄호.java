import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        for (int i = 0; i < num; i++) {
            System.out.println(vps(sc.next()));
        }
    }

    public static String vps(String string) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                st.push('(');
            } else if (st.empty()) {
                return "NO";
            } else {
                if (st.size() > 0) {
                    st.pop();
                }
            }
        }

        if (st.empty()) {
            return "YES";
        } else {
            return "NO";
        }
    }
}
