import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static class Person {
        int w;
        int h;
        int count;

        Person(int w, int h) {
            this.w = w;
            this.h = h;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Person> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new Person(sc.nextInt(), sc.nextInt()));
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (list.get(i).w < list.get(j).w && list.get(i).h < list.get(j).h) {
                    list.get(i).count++;

                }
            }
        }

        for (int i = 0; i < n; i++) {

            System.out.print(list.get(i).count + 1+" ");
        }

    }
}
