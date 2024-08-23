import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str1 = sc.nextLine();
        String str2 = str1.trim();

        String[] result = str2.split(" ");

        if (str2.equals("")) {
            System.out.println(0);
        } else {
            System.out.println(result.length);
        }
        
    }
}