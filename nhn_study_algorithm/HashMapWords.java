import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class HashMapWords {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // 해쉬맵 생성
        HashMap<String, String> hm = new HashMap<>(1000);

        //파일 읽기
        File file = new File("words.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                // 앞에 숫자빼고 영어랑 뜻만 해쉬맵에 저장
                String[] string = line.split(" |    |\t", 3);
                string[2] = string[2].trim();
                hm.put(string[1], string[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("사전 읽기 실패");
            sc.close();
            return;
        }
        
        // 단어 검색
        while (true) {
            System.out.print("검색할 단어를 입력하세요: ");
            String str = sc.nextLine();

            if (str.equals("exit()")) {
                break;
            }
            // 현재 단어가 있는지
            if (hm.containsKey(str)) {
                System.out.println("단어 뜻: " + hm.get(str) + "\n");
            } else {
                System.out.println("단어가 없습니다.\n");
            }
        }
        System.out.println("종료되었습니다.");
        sc.close();
    }
}
