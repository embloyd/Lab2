package lab2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("---Задание 1: Имена---");
        String last = readText(sc, "Введите фамилию (или оставьте пустым): ", true);
        String first = readText(sc, "Введите имя (обязательно): ", false);
        String middle = readText(sc, "Введите отчество (или оставьте пустым): ", true);

        Name person = new Name(last.isEmpty() ? null : last, first, middle.isEmpty() ? null : middle);
        System.out.println("Создано имя: " + person);

        System.out.println("---Задание 2: Дом---");
        int floors = readPositiveInt(sc, "Введите количество этажей: ");
        House house = new House(floors);
        System.out.println(house);

        System.out.println("---Задание 3: Сотрудники и отделы---");
        handleEmployees(sc);

        System.out.println("---Задание 5: Дроби---");

        System.out.println("Введите числитель и знаменатель первой дроби:");
        int num1 = readInt(sc, "Числитель: ");
        int den1 = readNonZeroInt(sc, "Знаменатель: ");
        Fraction f1 = new Fraction(num1, den1);

        System.out.println("Введите числитель и знаменатель второй дроби:");
        int num2 = readInt(sc, "Числитель: ");
        int den2 = readNonZeroInt(sc, "Знаменатель: ");
        Fraction f2 = new Fraction(num2, den2);

        System.out.println("Введите числитель и знаменатель третьей дроби:");
        int num3 = readInt(sc, "Числитель: ");
        int den3 = readNonZeroInt(sc, "Знаменатель: ");
        Fraction f3 = new Fraction(num3, den3);

        // Примеры операций
        System.out.println(f1 + " + " + f2 + " = " + f1.add(f2));
        System.out.println(f1 + " - " + f2 + " = " + f1.subtract(f2));
        System.out.println(f1 + " * " + f2 + " = " + f1.multiply(f2));
        System.out.println(f1 + " / " + f2 + " = " + f1.divide(f2));
        System.out.println(f1 + " + 5 = " + f1.add(5));
        System.out.println(f1 + " - 5 = " + f1.subtract(5));
        System.out.println(f1 + " * 5 = " + f1.multiply(5));
        System.out.println(f1 + " / 5 = " + f1.divide(5));

        // Выполнение выражения
        Fraction result = f1.add(f2).divide(f3).subtract(5);
        System.out.println("\nРезультат f1.sum(f2).div(f3).minus(5) = " + result);

        sc.close();
    }

    // Проверки
    private static String readText(Scanner sc, String prompt, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.isEmpty() && allowEmpty) return "";
            if (input.matches("[А-Яа-яA-Za-zёЁ\\s-]+")) return input;
            System.out.println("Ошибка: используйте только буквы.");
        }
    }

    private static int readPositiveInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value > 0) return value;
                System.out.println("Ошибка: введите положительное число.");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    // 3
    private static void handleEmployees(Scanner sc) {
        String deptName = readText(sc, "Введите название отдела: ", false);
        Department department = new Department(deptName);

        int n = readPositiveInt(sc, "Сколько сотрудников добавить: ");
        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {
            String empName = readText(sc, "Введите фамилию сотрудника " + (i + 1) + ": ", false);
            employees[i] = new Employee(empName);
            employees[i].setDepartment(department);
        }

        // Выбор начальника
        while (true) {
            System.out.println("\nСписок сотрудников:");
            for (int i = 0; i < n; i++) {
                System.out.println((i + 1) + ") " + employees[i].getName());
            }
            System.out.print("Введите номер начальника (1.." + n + "): ");
            String sel = sc.nextLine().trim();
            try {
                int idx = Integer.parseInt(sel);
                if (idx < 1 || idx > n) {
                    System.out.println("Ошибка: номер вне диапазона.");
                    continue;
                }
                department.setHead(employees[idx - 1]);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }

        System.out.println("\nИнформация о сотрудниках:");
        for (Employee e : department.getEmployees()) {
            System.out.println(e);
        }

        // сотрудники отдела
        String search = readText(sc, "\nВведите фамилию сотрудника, чтобы вывести всех сотрудников его отдела: ", false);
        Employee found = null;
        for (Employee e : department.getEmployees()) {
            if (e.getName().equalsIgnoreCase(search)) {
                found = e;
                break;
            }
        }

        if (found != null) {
            System.out.println("\nСотрудники отдела " + found.getDepartment().getName() + ":");
            for (Employee e : found.getDepartment().getEmployees()) {
                System.out.println("- " + e.getName());
            }
        } else {
            System.out.println("Сотрудник с такой фамилией не найден.");
        }

    }

    // 5
    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    private static int readNonZeroInt(Scanner sc, String prompt) {
        while (true) {
            int value = readInt(sc, prompt);
            if (value != 0) return value;
            System.out.println("Знаменатель не может быть 0, попробуйте снова.");
        }
    }

}
