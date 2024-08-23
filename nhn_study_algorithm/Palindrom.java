package algorithm;

public class Palindrom {
    public static boolean isPalindrom(int value) {

        int reverseNum = 0;
        int reminder;
        int originalNum = value;

        while (value != 0) {
            reminder = value % 10;
            reverseNum = reverseNum * 10 + reminder;
            value /= 10;
        }

        if (reverseNum == originalNum) {
            return true;
        } else
            return false;
    }
    // 뒤집었을때 똑같은지
    public static void main(String[] args) {

        int value = 101101;

        if(isPalindrom(value)){
            System.out.println("yes");
        }else{
            System.out.println("no");
        }
    }
}
