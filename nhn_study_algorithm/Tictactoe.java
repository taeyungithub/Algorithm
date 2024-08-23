package algorithm;

import java.util.Random;
import java.util.Scanner;

public class Tictactoe {

    // 이기는 모든 경우의 수
    public static boolean toWin(char[][] array){
        if (array[0][0] != '1'&& (array[0][0]== array[0][1] && array[0][1]==array[0][2])) {
            return true;
        } else if (array[1][0] != '1'&& (array[1][0]== array[1][1] && array[1][1]==array[1][2])) {
            return true;
        } else if (array[2][0] != '1'&& (array[2][0]== array[2][1] && array[2][1]==array[2][2])) {
            return true;
        } else if (array[0][0] != '1'&& (array[0][0]== array[1][0] && array[1][0]==array[2][0])) {
            return true;
        } else if (array[0][1] != '1'&& (array[0][1]== array[1][1] && array[1][1]==array[2][1])) {
            return true;
        } else if (array[0][2] != '1'&& (array[0][2]== array[1][2] && array[1][2]==array[2][2])) {
            return true;
        } else if (array[0][0] != '1'&& (array[0][0]== array[1][1] && array[1][1]==array[2][2])) {
            return true;
        } else if (array[0][2] != '1'&& (array[0][2]== array[1][1] && array[1][1]==array[2][0])) {
            return true;
        }
        return false;
    }

    // 틱택토 출력
    public static void printTictactoe(char[][] array){
        for(int i= 0; i<array.length;i++){
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == '1') {
                    System.out.print("   ");
                } else {
                    System.out.print(" "+array[i][j]+" ");
                }
                if(j != array[i].length -1 ){
                    System.out.print("|");
                }
            }
            if(i != array.length- 1){
                System.out.println();
                System.out.println("------------");
            }

        }
        System.out.println();
    }

    // 현재 틱택토가 꽉찾는지
    public static boolean isFull(char[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == '1') {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        // 숫자0이랑 헷갈려서 1로 초기화
        char[][] tictactoe = {{'1', '1', '1'}, {'1', '1', '1'}, {'1', '1', '1'}};

        boolean win = false; // 승자가 나왔는지
        int who = 0; // 누구 차례인지  (0은 사용자, 1은 컴퓨터)

        Scanner sc = new Scanner(System.in);
        Random random = new Random();   // 랜덤

        System.out.println("----Start----");    // 시작
        printTictactoe(tictactoe);  // 틱택토 출력

        // 승자가 나올 때까지
        while (win != true) {
            // 틱택토가 꽉차도 종료
            if (isFull(tictactoe)) {
                who = 2;
                break;
            }

            try{
                // 사용자 차례
                if (who == 0) {
                    System.out.println("\nMy turn");

                    while (true) {
                        // x y 입력
                        System.out.print("Enter the number (x, y): ");
                        int x = sc.nextInt();
                        int y = sc.nextInt();

                        // 빈곳 일 경우
                        if (tictactoe[x][y] == '1') {
                            tictactoe[x][y] = 'O';
                            //컴퓨터로 차례 넘기기
                            who = 1;
                            break;
                        } else {  // 이미 있을 경우
                            System.out.println("It's already there!!");
                        }
                    }
                    printTictactoe(tictactoe);
                    win = toWin(tictactoe); // 승자인지 아닌지 판별
                } else { // 컴퓨터 차례
                    System.out.println("\nComputer turn");
                    while (true) {
                        int x = random.nextInt(3);  // 0~3 무작위 값 리턴
                        int y = random.nextInt(3);

                        if (tictactoe[x][y] == '1') {
                            tictactoe[x][y] = 'X';
                            // 사용자로 차례 넘기기
                            who = 0;
                            break;
                        }
                    }
                    printTictactoe(tictactoe);
                    win = toWin(tictactoe);
                }
            }catch (ArrayIndexOutOfBoundsException e){ // 범위에 맞지않는 숫자를 입력했을때
                System.out.println("Out of boundary!! Try again");
            }
        }

        // 누군가 승자라고 판별이 됬을 때 차례가 넘어간 상태이다.

        if (who == 0) {
            System.out.println("\n---Computer Win---");
        }else if (who == 1){
            System.out.println("\n---User Win---");
        }else{ // 승자 판별이 안되고 꽉차면 무승부
            System.out.println("\n---draw---");
        }
    }
}
