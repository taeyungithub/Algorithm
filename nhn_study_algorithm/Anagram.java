package algorithm;

public class Anagram {

    // 1. 문자열을 char[]로 변환
    public static char[] toCharArray(String value) {
        char[] result = new char[value.length()];

        for (int i = 0; i < result.length; i++) {
            result[i] = value.charAt(i);
        }
        return result;
    }

    // 2. char[]에서 공백제거
    public static char[] removeWhiteSpace(char[] array) {
        int count = 0;
        for (char c : array) {
            if (c != ' ') {
                count++;
            }
        }
        char[] result = new char[count];
        int index = 0;

        for (char c : array) {
            if (c != ' ') {
                result[index++] = c;
            }
        }
        return result;
    }

    // 3. char[]에서 모든 문자를 소(대)문자로 변경
    public static char[] toLowerCase(char[] array) {
        char[] result = new char[array.length];
        int index = 0;

        for (char c : array) {
            if ((int) c < 97) {
                result[index++] = (char) (c + 32);
            } else {
                result[index++] = c;
            }
        }

        return result;
    }

    // 4. 내림차순(오름차순)으로 정렬한다.
    public static void sort(char[] arr) {
        char temp = '0';

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // 5. 비교
    public static boolean isEqual(char[] array1, char[] array2) {
        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAnagram(String value1, String value2) {
        char[] array1 = toLowerCase(removeWhiteSpace(toCharArray(value1)));
        char[] array2 = toLowerCase(removeWhiteSpace(toCharArray(value2)));

        sort(array1);
        sort(array2);

        return isEqual(array1, array2);

    }

    public static void main(String[] args) {
        String s1 = "i am Lord Voldemort";
        String s2 = "Tom Marvolo Riddle";

        System.out.println(isAnagram(s1, s2));
    }
}
