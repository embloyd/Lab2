package fixed;

/**
 * Сущность, представляющая дом.
 * Содержит информацию о количестве этажей.
 *
 * @author Студент
 * @version 1.0
 */
public class House
{

    private final int floors;

    /**
     * Конструктор дома.
     *
     * @param floors количество этажей (должно быть > 0)
     * @throws IllegalArgumentException если количество этажей <= 0
     */
    public House(int floors)
    {
        if (floors <= 0)
        {
            throw new IllegalArgumentException(
                    "Количество этажей должно быть положительным"
            );
        }
        this.floors = floors;
    }

    /**
     * Возвращает строковое представление дома.
     * Использует правильные окончания для числа этажей.
     *
     * @return строка вида "дом с N этажами/этажом"
     */
    @Override
    public String toString()
    {
        int n = floors % 100;
        String ending;

        if (n >= 11 && n <= 19)
        {
            ending = "этажами";
        }
        else
        {
            int last = n % 10;
            if (last == 1)
            {
                ending = "этажом";
            }
            else
            {
                ending = "этажами";
            }
        }

        return "дом с " + floors + " " + ending;
    }

}