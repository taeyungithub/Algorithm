import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] nums = new double[3];

        while (true) {
            for (int i = 0; i < 3; i++) {
                nums[i] = Math.pow(sc.nextInt(), 2);
            }
            Arrays.sort(nums);
            if (nums[0] == 0) {
                break;
            }
            if (nums[0] + nums[1] == nums[2]) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }


        }
    }
}