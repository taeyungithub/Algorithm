public class Card {
    Suit suit; // 문양
    int rank; // 숫자

    public Card(Suit suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public int getRank() {
        return this.rank;
    }

    // 보여줄때는 알파벳으로 바꿔서 보여주기
    public String toString() {
        String str;

        if (this.rank == 11) {
            str = "J";
        } else if (this.rank == 12) {
            str = "Q";
        } else if (this.rank == 13) {
            str = "K";
        } else if (this.rank == 14) {
            str = "A";
        } else {
            str = "" + this.rank;
        }

        return this.suit + " " + str + " ";
    }
}
