import java.util.Scanner;

public class RationalOperators2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Rational r1 = new Rational(sc.nextInt(), sc.nextInt());
        sc.nextLine();
        String str = sc.nextLine();
        Rational r2 = new Rational(sc.nextInt(), sc.nextInt());
        sc.close();

        Rational result = new Rational(1, 1);
        switch (str) {
            case "+":
                result = r1.add(r2);
                break;
            case "-":
                result = r1.subtract(r2);
                break;
            case "*":
                result = r1.times(r2);
                break;
            case "/":
                result = r1.divide(r2);
                break;
            default:
                break;
        }
        System.out.println(result);
    }
}

class Rational {

    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new RuntimeException("x");
        }
        int gCD = this.gCD(numerator, denominator);
        this.numerator = numerator / gCD;
        this.denominator = denominator / gCD;
    }

    private int gCD(int m, int n) {
        return (n == 0) ? m : gCD(n, m % n);
    }

    public String toString() {
        return this.numerator + " / " + this.denominator;
    }

    public void set(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Rational add(Rational rational) {
        int numerator = this.numerator * rational.getDenominator() + this.denominator * rational.getNumerator();
        int denominator = this.denominator * rational.getDenominator();

        return new Rational(numerator, denominator);
    }

    public Rational subtract(Rational rational) {
        int numerator = this.numerator * rational.getDenominator() - this.denominator * rational.getNumerator();
        int denominator = this.denominator * rational.getDenominator();

        return new Rational(numerator, denominator);
    }

    public Rational times(Rational rational) {
        int numerator = this.numerator * rational.getNumerator();
        int denominator = this.denominator * rational.getDenominator();

        return new Rational(numerator, denominator);
    }

    public Rational divide(Rational rational) {
        int numerator = this.numerator * rational.getDenominator();
        int denominator = this.denominator * rational.getNumerator();

        return new Rational(numerator, denominator);
    }
}
