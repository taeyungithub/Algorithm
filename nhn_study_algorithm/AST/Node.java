public class Node {
    String item;
    Node left;
    Node right;

    // 숫자 등록할때
    public Node(String item) {
        this.item = item;
        this.left = this.right = null;
    }

    // 연산자 등록할때
    public Node(String item, Node left, Node right) {
        this.item = item;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        //숫자면
        if (left == null && right == null) {
            return "(" + item + ")";
        }
        //연산자면
        return "(" + left + "," + item + "," + right + ")";
    }
}
