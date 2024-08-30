import java.util.*;

public class Poker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 카드 초기화
        Card[][] cards = card();
        // 카드 이미 뽑혔는지 확인하는 곳
        int[][] useCard = new int[4][15];

        // 인원수 입력
        System.out.print("Enter the number of people(2 ~ 5): ");
        int n = Integer.parseInt(sc.nextLine());

        @SuppressWarnings("unchecked")
        Person<Card>[] people = new Person[n];
        // 사용자 이름 입력
        System.out.print("User name: ");
        String name = sc.nextLine();
        Person<Card> user = new Person<>(name);
        people[0] = user;

        sc.close();

        // 나머지 만들어 넣기
        for (int i = 1; i < n; i++) {
            people[i] = new Person<>();
        }

        // 카드 5장씩 받기
        drawCard(people, cards, useCard);

        // 각 플레이어의 족보 결정
        for (Person<Card> person : people) {
            person.determineRank();
        }

        // 뽑은 카드와 족보 출력
        System.out.println("\nCurrent Status");
        for (Person<Card> person : people) {
            System.out.println(person);
        }

        // 가장 높은 족보를 가진 플레이어 찾기
        Person<Card> winner = findWinner(people);
        System.out.println("\nWinner: " + winner.getName() + " with " + winner.getHandRank() + "\n");
    }

    // 카드 뽑기
    public static void drawCard(Person<Card>[] people, Card[][] cards, int[][] useCard) {
        Random random = new Random();
        for (Person<Card> person : people) {
            for (int j = 0; j < 5; j++) {
                while (true) {
                    int a = random.nextInt(4);
                    int b = random.nextInt(13) + 2;
                    if (useCard[a][b] == 0) {
                        person.addCard(cards[a][b]);
                        useCard[a][b] = 1;
                        break;
                    }
                }
            }
        }
    }

    // 카드 초기화
    public static Card[][] card() {
        Card[][] card = new Card[4][15];

        for (int i = 0; i < 4; i++) {
            Suit suit = Suit.values()[i]; // enum을 사용하여 문양 설정

            // J = 11, Q = 12, K = 13, A = 14로 설정
            for (int j = 2; j <= 14; j++) {
                Card newCard = new Card(suit, j);
                card[i][j] = newCard;
            }
        }
        return card;
    }

    // 가장 높은 족보를 가진 플레이어 찾기
    public static Person<Card> findWinner(Person<Card>[] people) {
        // 족보 순위를 Map으로 만듬
        Map<String, Integer> handRankMap = new HashMap<>();
        handRankMap.put("High Card", 0);
        handRankMap.put("One Pair", 1);
        handRankMap.put("Two Pair", 2);
        handRankMap.put("Three of a Kind", 3);
        handRankMap.put("Straight", 4);
        handRankMap.put("Flush", 5);
        handRankMap.put("Full House", 6);
        handRankMap.put("Four of a Kind", 7);
        handRankMap.put("Straight Flush", 8);

        // 처음 승자를 나로 설정
        Person<Card> winner = people[0];
        int highestRank = handRankMap.get(winner.getHandRank());
        for (Person<Card> person : people) { // 한 사람씩 비교
            int currentRank = handRankMap.get(person.getHandRank());
            if (currentRank > highestRank) {
                winner = person;
                highestRank = currentRank;
            } else if (currentRank == highestRank) { // 족보가 동일할 때 카드 비교
                for (int i = 0; i < winner.kicker.size(); i++) { // 같으면 다음 수를 비교하면 됨
                    if (person.kicker.get(i) > winner.kicker.get(i)) {
                        winner = person;
                        highestRank = currentRank;
                        break;
                    } else if (person.kicker.get(i) < winner.kicker.get(i)) {
                        break;
                    }
                }
            }
        }
        return winner;
    }
}
