public class Calculate {

    public double evaluation(Node ast) {
        // 빈 노드는 0을 반환
        if (ast == null) {
            return 0;
        }

        // left, right가 null이면 숫자
        if (ast.left == null && ast.right == null) {
            return Integer.parseInt(ast.item);
        }

        // 후위 순회 방식으로 왼쪽과 오른쪽 자식의 값을 계산
        double leftItem = evaluation(ast.left);
        double rightItem = evaluation(ast.right);

        // 연산자에 따라 계산
        switch (ast.item) {

            case "+":
                return leftItem + rightItem;
            case "-":
                return leftItem - rightItem;
            case "*":
                return leftItem * rightItem;
            case "/":
                // 0으로 나누면 오류
                if (rightItem == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return leftItem / rightItem;
            default:
                return 0;
        }
    }
}
