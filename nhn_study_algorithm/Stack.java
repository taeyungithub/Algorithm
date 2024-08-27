import java.util.Scanner;

class createStack {
    private int size;
    private int[] stack;

    createStack() {
        stack = new int[10];
        size = 0;
    }

    public void push(int n) {
        if (this.size >= stack.length) {
            throw new IllegalArgumentException("Stack full");
        }
        this.stack[this.size++] = n;
    }

    public int pop() {
        if (this.size == 0) {
            throw new IllegalArgumentException("Stack empty");
        }
        return this.stack[--this.size];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int top() {
        if (this.size == 0) {
            throw new IllegalArgumentException("Stack empty");
        }
        return stack[size - 1];
    }

}

public class Stack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        createStack stack = new createStack();

        int n = 1;

        while (n != 0) {
            System.out.println("Choose (1.push, 2.pop, 3.top, 4.isEmpty 0.exit)");
            int str = sc.nextInt();

            switch (str) {
                case 1:
                    System.out.print("Enter the number: ");
                    int a = sc.nextInt();
                    stack.push(a);
                    System.out.println("Insert: " + a);

                    break;

                case 2:
                    int num = stack.pop();
                    System.out.println("Remove: " + num);
                    break;

                case 3:
                    System.out.println("Last number is " + stack.top());
                    break;
                case 4:
                    if (stack.isEmpty()) {
                        System.out.println("Stack is Empty");
                    } else {
                        System.out.println("Stack is not Empty");
                    }
                    break;

                default:
                    n = 0;
                    break;
            }
            System.out.println();
        }
        System.out.println("End");
    }
}