package fixed;

/**
 * Сущность, представляющая математическую дробь.
 * Поддерживает арифметические операции с дробями и целыми числами.
 *
 * @author Студент
 * @version 1.0
 */
public class Fraction
{

    private final int numerator;
    private final int denominator;

    /**
     * Конструктор дроби.
     *
     * @param numerator числитель
     * @param denominator знаменатель (не может быть 0)
     * @throws IllegalArgumentException если знаменатель равен 0
     */
    public Fraction(int numerator, int denominator)
    {
        if (denominator == 0)
        {
            throw new IllegalArgumentException(
                    "Знаменатель не может быть 0"
            );
        }

        if (denominator < 0)
        {
            numerator = -numerator;
            denominator = -denominator;
        }

        int gcd = gcd(Math.abs(numerator), denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    /**
     * Вычисляет наибольший общий делитель.
     *
     * @param a первое число
     * @param b второе число
     * @return НОД чисел a и b
     */
    private int gcd(int a, int b)
    {
        while (b != 0)
        {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    /**
     * Возвращает строковое представление дроби.
     *
     * @return строка вида "числитель/знаменатель"
     */
    @Override
    public String toString()
    {
        return numerator + "/" + denominator;
    }

    /**
     * Складывает текущую дробь с другой.
     *
     * @param other другая дробь
     * @return новая дробь - результат сложения
     */
    public Fraction add(Fraction other)
    {
        int num = this.numerator * other.denominator
                + other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    /**
     * Вычитает другую дробь из текущей.
     *
     * @param other вычитаемая дробь
     * @return новая дробь - результат вычитания
     */
    public Fraction subtract(Fraction other)
    {
        int num = this.numerator * other.denominator
                - other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    /**
     * Умножает текущую дробь на другую.
     *
     * @param other множитель
     * @return новая дробь - результат умножения
     */
    public Fraction multiply(Fraction other)
    {
        int num = this.numerator * other.numerator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    /**
     * Делит текущую дробь на другую.
     *
     * @param other делитель
     * @return новая дробь - результат деления
     * @throws IllegalArgumentException если деление на ноль
     */
    public Fraction divide(Fraction other)
    {
        if (other.numerator == 0)
        {
            throw new IllegalArgumentException("Деление на ноль");
        }
        int num = this.numerator * other.denominator;
        int den = this.denominator * other.numerator;
        return new Fraction(num, den);
    }

    /**
     * Складывает дробь с целым числом.
     *
     * @param n целое число
     * @return новая дробь - результат сложения
     */
    public Fraction add(int n)
    {
        return add(new Fraction(n, 1));
    }

    /**
     * Вычитает целое число из дроби.
     *
     * @param n вычитаемое целое число
     * @return новая дробь - результат вычитания
     */
    public Fraction subtract(int n)
    {
        return subtract(new Fraction(n, 1));
    }

    /**
     * Умножает дробь на целое число.
     *
     * @param n множитель
     * @return новая дробь - результат умножения
     */
    public Fraction multiply(int n)
    {
        return multiply(new Fraction(n, 1));
    }

    /**
     * Делит дробь на целое число.
     *
     * @param n делитель (не может быть 0)
     * @return новая дробь - результат деления
     * @throws IllegalArgumentException если деление на ноль
     */
    public Fraction divide(int n)
    {
        if (n == 0)
        {
            throw new IllegalArgumentException("Деление на ноль");
        }
        return divide(new Fraction(n, 1));
    }

}