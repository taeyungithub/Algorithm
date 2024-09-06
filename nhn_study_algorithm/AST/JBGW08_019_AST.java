import java.util.Scanner;
import java.util.Stack;

public class JBGW08_019_AST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        while (true) {

            System.out.print("> ");
            String expression = sc.nextLine();

            if (expression.equals("exit()")) {
                break;
            }
            // ast 만들기
            System.out.println("AST: " + generateAST(expression));

            // 계산 클래스
            Calculate calculate = new Calculate();

            double result = calculate.evaluation(generateAST(expression));

            System.out.println("계산값: " + result);
        }

    }

    public static Node generateAST(String expression) {
        // 숫자넣는 스택, 연산자 넣는 스택
        Stack<Node> number = new Stack<>();
        Stack<String> operator = new Stack<>();

        String[] array = expression.split(" ");

        // 트리 만들기 (후위표기법 이용)
        for (String s : array) {
            try {
                // 숫자인지 확인후에 변환되면 스택에 넣음
                Integer.parseInt(s);
                number.push(new Node(s));
            } catch (NumberFormatException e) {
                // 숫자가 아니면 연산자
                if (s.equals("+") || s.equals("-")) {
                    // + - 보다 * / 가 우선순위가 높기 때문에
                    while (!operator.isEmpty() && (operator.peek().equals("*") || operator.peek().equals("/"))) {
                        Node right = number.pop();
                        Node left = number.pop();
                        String op = operator.pop();
                        Node newNode = new Node(op, left, right);
                        number.push(newNode);
                    }
                    operator.push(s);  // +, - 는 스택에 넣기
                } else if (s.equals("*") || s.equals("/")) {
                    operator.push(s);  // *, / 는 그대로 스택에 넣기
                } else { // 연산자가 아니면 오류
                    throw new IllegalArgumentException("잘못된 연산자: " + s);
                }
            }
        }

        // 나머지
        while (!operator.isEmpty()) {
            Node right = number.pop();
            Node left = number.pop();
            String op = operator.pop();
            Node newNode = new Node(op, left, right);
            number.push(newNode);
        }

        // 마지막 노드 (루트)
        return number.pop();
    }
}
