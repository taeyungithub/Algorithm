import java.util.*;

public class Person<E extends Card> implements Iterable<E> {
    public String name; // 사람이름
    public List<E> cards; // 사람마다 5장카드 보관
    public String HankRank; // 족보(순위) 저장하는 곳
    public List<Integer> kicker; // 족보가 겹쳤을때 비교할때 필요함

    public Person() {
        this.cards = new ArrayList<>();
        this.name = RandomName();
        this.HankRank = "";
    }

    public Person(String name) {
        this.cards = new ArrayList<>();
        this.name = name;
        this.HankRank = "";
    }

    public void addCard(E card) {
        this.cards.add(card);
    }

    public String getName() {
        return name;
    }

    // 카드 정렬
    public void sort() {
        this.cards.sort((x, y) -> {
            int rankComparison = x.getRank() - y.getRank();
            if (rankComparison == 0) {
                // rank가 같으면 Suit로 비교
                return y.getSuit().ordinal() - x.getSuit().ordinal();
            } else {
                return rankComparison;
            }
        });
    }

    // 족보로 순위 판별
    public void determineRank() {
        kicker = new ArrayList<>(); // 키커 생성
        sort(); // 정렬된 상태로 족보를 판별하기 위해

        // 숫자가 몇번 뽑혔는지를 저장할 리스트 (인덱스 0~1은 사용하지 않음, 2~14 사용)
        List<Integer> rankCount = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            rankCount.add(0);
        }

        boolean flush = true;
        boolean straight = true;

        for (int i = 0; i < cards.size(); i++) {
            int currentRank = cards.get(i).getRank();
            rankCount.set(currentRank, rankCount.get(currentRank) + 1); // 해당 숫자의 빈도 증가

            if (i > 0) {
                // (현재와 전 카드만 비교하면 됨)
                if (cards.get(i).getSuit() != cards.get(i - 1).getSuit()) {
                    flush = false; // 무늬가 같지 않으면
                }
                if (cards.get(i).getRank() != cards.get(i - 1).getRank() + 1) {
                    straight = false; // 숫자가 연속되지 않으면
                }
            }
        }

        // 족보 판별과 키커
        // 족보가 같을 수 없는 것들을 제외하고 족보가 같을수 있는 것들은 페어카드 숫자를 비교후 같으면 나머지카드의 숫자를 비교하도록 하였음
        // 따라서 키커에 페어카드의 숫자를 넣고 페어카드도 같을 수 있으니까 페어카드가 아닌 것들을 내림차순으로 넣음
        if (flush && straight) {
            this.HankRank = "Straight Flush";
            kicker.add(cards.get(4).getRank()); // 같은 족보가 나와도 제일 큰수만 비교하면 되기때문에 마지막 카드를 저장

        } else if (rankCount.contains(4)) {
            this.HankRank = "Four of a Kind";
            kicker.add(rankCount.indexOf(4)); // 빈도가 4번인 인덱스(카드 숫자)를 키커에 저장
            kicker.add(rankCount.indexOf(1)); // 나머지인 빈도 1인 인덱스를 저장

        } else if (rankCount.contains(3) && rankCount.contains(2)) {
            this.HankRank = "Full House";
            kicker.add(rankCount.indexOf(3)); // 빈도가 3번인 인덱스(카드 숫자)를 키커에 저장 (상대와 겹칠 일이 없음)

        } else if (flush) {
            this.HankRank = "Flush";
            kicker.add(cards.get(4).getRank()); // 무늬가 같기 떄문에 가장 큰수만 저장

        } else if (straight) {
            this.HankRank = "Straight";
            kicker.add(cards.get(4).getRank()); // 숫자가 이어지므로 가장 큰수만 저장

        } else if (rankCount.contains(3)) {
            this.HankRank = "Three of a Kind";
            kicker.add(rankCount.indexOf(3)); // 빈도가 3번인 인덱스(카드 숫자)를 키커에 저장 (상대와 겹칠 일이 없음)

        } else if (Collections.frequency(rankCount, 2) == 2) { // 빈도가 2번인 숫자가 2개인지
            this.HankRank = "Two Pair";
            List<Integer> pair = new ArrayList<>();
            for (int i = 0; i < rankCount.size(); i++) { // 2개를 다른 리스트에 저장
                if (rankCount.get(i) == 2) {
                    pair.add(i);
                }
            }
            kicker.add(Collections.max(pair)); // 키커에는 큰수부터 넣음 (먼저 비교하기 위해)
            kicker.add(Collections.min(pair));
            kicker.add(rankCount.indexOf(1)); // 나머지 1개 저장 (페어 둘다 같을 경우 대비)
        } else if (rankCount.contains(2)) {
            this.HankRank = "One Pair";
            List<Integer> pair = new ArrayList<>();
            kicker.add(rankCount.indexOf(2)); // 페어카드 숫자 저장
            for (int i = 0; i < rankCount.size(); i++) {
                if (rankCount.get(i) == 1) {
                    pair.add(i);
                }
            }
            Collections.sort(pair);
            for (int i = 2; i > -1; i--) { // 나머지 큰수부터 저장
                kicker.add(pair.get(i));
            }

        } else {
            this.HankRank = "High Card";
            for (int i = 4; i > -1; i--) { // 큰수부터 비교하기 위해
                kicker.add(cards.get(i).getRank());
            }
        }
    }

    public String getHandRank() {
        return HankRank;
    }

    public Iterator<E> iterator() {
        return this.cards.iterator();
    }

    @Override
    public String toString() {
        return name + ": " + cards + " (" + HankRank + ")";
    }

    // 랜덤으로 이름 만들기
    public static String RandomName() {
        String[] nameStr = { "Olivia", "Emma", "Ava", "Mia", "Luna", "Aria", "Lucas", "Levi", "James", "Jack", "Logan", "Jackson", "Mateo" };

        Random random = new Random();
        String name;
        int index = random.nextInt(nameStr.length);
        name = nameStr[index];
        int alpha = random.nextInt(26) + 65;
        return (char) alpha + "." + name;
    }
}
