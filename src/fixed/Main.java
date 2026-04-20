package fixed;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Главный класс приложения.
 * Выполняет демонстрацию всех заданий лабораторной работы.
 *
 * @author Студент
 * @version 1.0
 */
public class Main
{

    /**
     * Точка входа в программу.
     * Запускает все задания последовательно.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // задание 1: имена
        System.out.println("---Задание 1: Имена---");
        runNameTask(sc);

        // задание 2: дом
        System.out.println("\n---Задание 2: Дом---");
        runHouseTask(sc);

        // задание 3-4: сотрудники и отделы
        System.out.println("\n---Задание 3-4: Сотрудники и отделы---");
        runEmployeesTask(sc);

        // задание 5: дроби
        System.out.println("\n---Задание 5: Дроби---");
        runFractionTask(sc);

        sc.close();
    }

    /**
     * Выполняет задание по созданию имени.
     *
     * @param sc сканер для ввода данных
     */
    private static void runNameTask(Scanner sc)
    {
        String last = readText(
                sc,
                "Введите фамилию (или оставьте пустым): ",
                true
        );
        String first = readText(
                sc,
                "Введите имя (обязательно): ",
                false
        );
        String middle = readText(
                sc,
                "Введите отчество (или оставьте пустым): ",
                true
        );

        Name person = new Name(
                last.isEmpty() ? null : last,
                first,
                middle.isEmpty() ? null : middle
        );

        System.out.println("Создано имя: " + person);
    }

    /**
     * Выполняет задание по созданию дома.
     *
     * @param sc сканер для ввода данных
     */
    private static void runHouseTask(Scanner sc)
    {
        int floors = readPositiveInt(
                sc,
                "Введите количество этажей: "
        );
        House house = new House(floors);
        System.out.println(house);
    }

    /**
     * Выполняет задание по созданию сотрудников и отделов.
     *
     * @param sc сканер для ввода данных
     */
    private static void runEmployeesTask(Scanner sc)
    {
        String deptName = readText(
                sc,
                "Введите название отдела: ",
                false
        );
        Department department = new Department(deptName);

        int n = readPositiveInt(
                sc,
                "Сколько сотрудников добавить: "
        );
        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++)
        {
            String empName = readText(
                    sc,
                    "Введите фамилию сотрудника " + (i + 1) + ": ",
                    false
            );
            employees[i] = new Employee(empName);
            employees[i].setDepartment(department);
        }

        // выбор начальника отдела
        selectDepartmentHead(sc, employees, department);

        System.out.println("\nИнформация о сотрудниках:");
        for (Employee e : department.getEmployees())
        {
            System.out.println(e);
        }

        // поиск сотрудников отдела
        searchDepartmentEmployees(sc, department);
    }

    /**
     * Выбирает начальника отдела из списка сотрудников.
     *
     * @param sc сканер для ввода данных
     * @param employees массив сотрудников
     * @param department отдел
     */
    private static void selectDepartmentHead(
            Scanner sc,
            Employee[] employees,
            Department department
    )
    {
        while (true)
        {
            System.out.println("\nСписок сотрудников:");
            for (int i = 0; i < employees.length; i++)
            {
                System.out.println(
                        (i + 1) + ") " + employees[i].getName()
                );
            }
            System.out.print(
                    "Введите номер начальника (1.." + employees.length + "): "
            );

            String sel = sc.nextLine().trim();

            try
            {
                int idx = Integer.parseInt(sel);
                if (idx < 1 || idx > employees.length)
                {
                    System.out.println("Ошибка: номер вне диапазона.");
                    continue;
                }
                department.setHead(employees[idx - 1]);
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Ошибка: введите число.");
            }
        }
    }

    /**
     * Ищет сотрудника и выводит всех сотрудников его отдела.
     *
     * @param sc сканер для ввода данных
     * @param department отдел для поиска
     */
    private static void searchDepartmentEmployees(
            Scanner sc,
            Department department
    )
    {
        String search = readText(
                sc,
                "\nВведите фамилию сотрудника для поиска отдела: ",
                false
        );

        Employee found = null;
        for (Employee e : department.getEmployees())
        {
            if (e.getName().equalsIgnoreCase(search))
            {
                found = e;
                break;
            }
        }

        if (found != null)
        {
            System.out.println(
                    "\nСотрудники отдела "
                            + found.getDepartment().getName() + ":"
            );
            for (Employee e : found.getDepartment().getEmployees())
            {
                System.out.println("- " + e.getName());
            }
        }
        else
        {
            System.out.println("Сотрудник с такой фамилией не найден.");
        }
    }

    /**
     * Выполняет задание по работе с дробями.
     *
     * @param sc сканер для ввода данных
     */
    private static void runFractionTask(Scanner sc)
    {
        // создание трех дробей
        Fraction f1 = createFraction(sc, "первой");
        Fraction f2 = createFraction(sc, "второй");
        Fraction f3 = createFraction(sc, "третьей");

        // демонстрация операций
        demonstrateFractionOperations(f1, f2, f3);

        // вычисление сложного выражения
        Fraction result = f1.add(f2)
                .divide(f3)
                .subtract(5);

        System.out.println(
                "\nРезультат f1.sum(f2).div(f3).minus(5) = " + result
        );
    }

    /**
     * Создает дробь с проверкой ввода.
     *
     * @param sc сканер для ввода данных
     * @param order порядковый номер дроби
     * @return созданная дробь
     */
    private static Fraction createFraction(Scanner sc, String order)
    {
        System.out.println(
                "Введите числитель и знаменатель " + order + " дроби:"
        );
        int num = readInt(sc, "Числитель: ");
        int den = readNonZeroInt(sc, "Знаменатель: ");
        return new Fraction(num, den);
    }

    /**
     * Демонстрирует все операции с дробями.
     *
     * @param f1 первая дробь
     * @param f2 вторая дробь
     * @param f3 третья дробь
     */
    private static void demonstrateFractionOperations(
            Fraction f1,
            Fraction f2,
            Fraction f3
    )
    {
        // операции между дробями
        System.out.println(f1 + " + " + f2 + " = " + f1.add(f2));
        System.out.println(f1 + " - " + f2 + " = " + f1.subtract(f2));
        System.out.println(f1 + " * " + f2 + " = " + f1.multiply(f2));
        System.out.println(f1 + " / " + f2 + " = " + f1.divide(f2));

        // операции с целыми числами
        System.out.println(f1 + " + 5 = " + f1.add(5));
        System.out.println(f1 + " - 5 = " + f1.subtract(5));
        System.out.println(f1 + " * 5 = " + f1.multiply(5));
        System.out.println(f1 + " / 5 = " + f1.divide(5));
    }

    /**
     * Читает текстовую строку с проверкой.
     *
     * @param sc сканер для ввода
     * @param prompt приглашение к вводу
     * @param allowEmpty разрешена ли пустая строка
     * @return введенная строка
     */
    private static String readText(
            Scanner sc,
            String prompt,
            boolean allowEmpty
    )
    {
        while (true)
        {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            if (input.isEmpty() && allowEmpty)
            {
                return "";
            }

            if (input.matches("[А-Яа-яA-Za-zёЁ\\s-]+"))
            {
                return input;
            }

            System.out.println("Ошибка: используйте только буквы.");
        }
    }

    /**
     * Читает целое положительное число.
     *
     * @param sc сканер для ввода
     * @param prompt приглашение к вводу
     * @return положительное целое число
     */
    private static int readPositiveInt(Scanner sc, String prompt)
    {
        while (true)
        {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            try
            {
                int value = Integer.parseInt(input);
                if (value > 0)
                {
                    return value;
                }
                System.out.println("Ошибка: введите положительное число.");
            }
            catch (NumberFormatException e)
            {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    /**
     * Читает целое число.
     *
     * @param sc сканер для ввода
     * @param prompt приглашение к вводу
     * @return целое число
     */
    private static int readInt(Scanner sc, String prompt)
    {
        while (true)
        {
            System.out.print(prompt);
            try
            {
                return Integer.parseInt(sc.nextLine().trim());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    /**
     * Читает ненулевое целое число.
     *
     * @param sc сканер для ввода
     * @param prompt приглашение к вводу
     * @return ненулевое целое число
     */
    private static int readNonZeroInt(Scanner sc, String prompt)
    {
        while (true)
        {
            int value = readInt(sc, prompt);
            if (value != 0)
            {
                return value;
            }
            System.out.println("Знаменатель не может быть 0, повторите.");
        }
    }

}