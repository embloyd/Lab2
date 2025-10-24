package lab2;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) throw new IllegalArgumentException("Знаменатель не может быть 0");
        // если знаменатель отрицательный, переносим минус в числитель
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify(); // упрощаем дробь
    }

    // Упрощение через НОД
    private void simplify() {
        int gcd = gcd(Math.abs(numerator), denominator); //вычисляем НОД
        numerator /= gcd; // делим числ на нод
        denominator /= gcd; //знаменатель
    }

    private int gcd(int a, int b) {  // Метод нахождения НОД
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    // Операции с другой дробью
    public Fraction add(Fraction other) { // +
        int num = this.numerator * other.denominator + other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den); // Возвращаем новую дробь
    }

    public Fraction subtract(Fraction other) { // -
        int num = this.numerator * other.denominator - other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public Fraction multiply(Fraction other) { // *
        int num = this.numerator * other.numerator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public Fraction divide(Fraction other) { // деление
        if (other.numerator == 0) throw new IllegalArgumentException("Деление на ноль");
        int num = this.numerator * other.denominator;
        int den = this.denominator * other.numerator;
        return new Fraction(num, den);
    }

    // Операции с целым числом
    public Fraction add(int n) { // +
        return add(new Fraction(n, 1));
    }

    public Fraction subtract(int n) { // -
        return subtract(new Fraction(n, 1));
    }

    public Fraction multiply(int n) { // *
        return multiply(new Fraction(n, 1));
    }

    public Fraction divide(int n) { // деление
        if (n == 0) throw new IllegalArgumentException("Деление на ноль");
        return divide(new Fraction(n, 1));
    }
}
