package algorithm;

public class BubbleSort {

    public static void main(String[] args) {
        int[] array = { 9, 5, 1, 7, 6, 3, 2, 4, 8 };

        ascending(array);
        printArray(array);

        System.out.println();

        descending(array);
        printArray(array);

    }

    // 오름차순
    public static void ascending(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {

                if (array[j] > array[j + 1]) {
                    int temp = 0;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // 내림차순
    public static void descending(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {

                if (array[j] < array[j + 1]) {
                    int temp = 0;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
