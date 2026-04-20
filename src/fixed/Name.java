package fixed;

/**
 * Сущность, представляющая имя человека.
 * Содержит фамилию, личное имя и отчество.
 *
 * @author Студент
 * @version 2.0
 */
public class Name
{

    private final String lastName;
    private final String firstName;
    private final String middleName;

    /**
     * Конструктор с тремя параметрами.
     *
     * @param lastName фамилия (может быть null)
     * @param firstName личное имя (обязательно)
     * @param middleName отчество (может быть null)
     */
    public Name(String lastName, String firstName, String middleName)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    /**
     * Конструктор с двумя параметрами.
     *
     * @param lastName фамилия
     * @param firstName личное имя
     */
    public Name(String lastName, String firstName)
    {
        this(lastName, firstName, null);
    }

    /**
     * Конструктор с одним параметром (только имя).
     *
     * @param firstName личное имя
     */
    public Name(String firstName)
    {
        this(null, firstName, null);
    }

    /**
     * Возвращает строковое представление имени.
     * Не заданные параметры не включаются в вывод.
     *
     * @return строка вида "Фамилия Имя Отчество"
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        if (lastName != null && !lastName.isEmpty())
        {
            sb.append(lastName).append(" ");
        }

        if (firstName != null && !firstName.isEmpty())
        {
            sb.append(firstName).append(" ");
        }

        if (middleName != null && !middleName.isEmpty())
        {
            sb.append(middleName);
        }

        return sb.toString().trim();
    }

}