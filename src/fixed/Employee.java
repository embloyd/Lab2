package fixed;

/**
 * Сущность, представляющая сотрудника компании.
 * Содержит имя и ссылку на отдел.
 *
 * @author Студент
 * @version 1.0
 */
public class Employee
{

    private final String name;
    private Department department;

    /**
     * Конструктор сотрудника.
     *
     * @param name имя сотрудника
     */
    public Employee(String name)
    {
        this.name = name;
    }

    /**
     * Устанавливает отдел сотрудника.
     * Автоматически добавляет сотрудника в отдел.
     *
     * @param department отдел
     */
    public void setDepartment(Department department)
    {
        this.department = department;
        if (department != null)
        {
            department.addEmployee(this);
        }
    }

    /**
     * Возвращает имя сотрудника.
     *
     * @return имя сотрудника
     */
    public String getName()
    {
        return name;
    }

    /**
     * Возвращает отдел сотрудника.
     *
     * @return отдел сотрудника
     */
    public Department getDepartment()
    {
        return department;
    }

    /**
     * Возвращает строковое представление сотрудника.
     * Учитывает, является ли сотрудник начальником отдела.
     *
     * @return строка с информацией о сотруднике
     */
    @Override
    public String toString()
    {
        if (department == null)
        {
            return name + " не прикреплён к отделу";
        }

        if (department.getHead() == this)
        {
            return name + " — начальник отдела " + department.getName();
        }
        else
        {
            return name + " работает в отделе " + department.getName()
                    + ", начальник которого " + department.getHead().getName();
        }
    }

}