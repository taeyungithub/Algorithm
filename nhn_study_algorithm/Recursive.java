package algorithm;

// 재귀 함수

public class Recursive {
    public static int addNto10(int n) {
        int result = 0;
        for (int i = n; i <= 10; i++) {
            result += i;
        }
        return result;
    }

    public static int addNtoNRecur(int n) {
        if (n > 10) {
            return 0;
        } else {
            return addNtoNRecur(n + 1)+n;
        }
    }

    public static void main(String[] args) {
        System.out.println(addNto10(1));
        System.out.println(addNtoNRecur(1));
    }
}
