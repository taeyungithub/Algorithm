package algorithm;

public class Overloading {

    // 매서드 오버로딩
    public static int max(int i, int j) {
        return i > j ? i : j;
    }

    public static long max(long i, long j) {
        return i > j ? i : j;
    }

    public static int max(int i, int j, int k) {
        // int temp = 0;
        // temp = (i > j) ? i : j;
        // return (temp > k) ? temp : k;
        return max(max(i, j), k);
    }

    // 개수 상관없이 (가변길이 파라미터) -> 배열취급
    public static int max(int... values) {
        int temp = 0;
        for (int i : values) {
            temp = max(temp, i);
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(max(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

}
