package fixed;

import java.util.ArrayList;
import java.util.List;

/**
 * Сущность, представляющая отдел компании.
 * Содержит название, начальника и список сотрудников.
 *
 * @author Студент
 * @version 1.0
 */
public class Department
{

    private final String name;
    private Employee head;
    private final List<Employee> employees;

    /**
     * Конструктор отдела.
     *
     * @param name название отдела
     */
    public Department(String name)
    {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    /**
     * Устанавливает начальника отдела.
     *
     * @param head сотрудник-начальник
     */
    public void setHead(Employee head)
    {
        this.head = head;
        addEmployee(head);
    }

    /**
     * Добавляет сотрудника в отдел.
     *
     * @param e добавляемый сотрудник
     */
    public void addEmployee(Employee e)
    {
        if (!employees.contains(e))
        {
            employees.add(e);
        }
    }

    /**
     * Возвращает название отдела.
     *
     * @return название отдела
     */
    public String getName()
    {
        return name;
    }

    /**
     * Возвращает начальника отдела.
     *
     * @return сотрудник-начальник
     */
    public Employee getHead()
    {
        return head;
    }

    /**
     * Возвращает список сотрудников отдела.
     *
     * @return список сотрудников
     */
    public List<Employee> getEmployees()
    {
        return employees;
    }

    /**
     * Возвращает строковое представление отдела.
     *
     * @return строка с информацией об отделе
     */
    @Override
    public String toString()
    {
        String headName = (head != null) ? head.getName() : "не назначен";
        return "Отдел " + name + ", начальник: " + headName;
    }

}