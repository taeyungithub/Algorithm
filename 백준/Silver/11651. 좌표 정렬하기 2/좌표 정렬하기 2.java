import java.util.*;

public class Main {

    public static class coordinate {
        int x;
        int y;
        int count;

        coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<coordinate> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new coordinate(sc.nextInt(), sc.nextInt()));
        }

        list.sort(new Comparator<coordinate>() {
            @Override
            public int compare(coordinate c1, coordinate c2) {
                int result = c1.y - c2.y;
                if (result == 0) {
                    return c1.x - c2.x;
                } else {
                    return result;
                }
            }
        });
        
        for (int i = 0; i < n; i++) {
            System.out.println(list.get(i));
        }
    }
}
