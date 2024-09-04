import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ListWords {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Pair> list = new ArrayList<>();

        // 파일 읽기
        File file = new File("words.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                // 앞에 숫자빼고 영어랑 뜻만 해쉬맵에 저장
                String[] string = line.split(" |    |\t", 3);
                string[2] = string[2].trim();
                // hm.put(string[1], string[2]);
                Pair pair = new Pair(string[1].toLowerCase(), string[2]);
                list.add(pair);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("사전 읽기 실패");
            sc.close();
            return;
        }

        // 단어 정렬까지 완료
        Collections.sort(list);

        // 단어 검색
        while (true) {
            System.out.print("검색할 단어를 입력하세요: ");
            String str = sc.nextLine().toLowerCase();

            if (str.equals("exit()")) {
                break;
            }

            // 이진탐색
            int left = 0;
            int right = list.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (list.get(mid).getKey().compareTo(str) == 0) {
                    System.out.println("단어 뜻: " + list.get(mid).getValue());
                    break;
                } else if (list.get(mid).getKey().compareTo(str) < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if (left > right) {
                System.out.println("단어가 없습니다 ");

            }
        }

        System.out.println("종료되었습니다.");
        sc.close();
    }

}
