# Мурай Анастасия ИТ-3 Лабораторная №2
# Задание 1
## Задача 3
### Текст задачи
Создайте	сущность	Имя,	которая	описывается	тремя	параметрами:	Фамилия,	Личное	имя,	
Отчество.	Имя	может	быть	приведено	к	строковому	виду,	включающему	традиционное	
представление	всех	трех	параметров:	Фамилия	Имя	Отчество	(например	“Иванов	Иван	
Иванович”).	Необходимо	предусмотреть	возможность	того,	что	какой-либо	из	параметров	может	
быть	не	задан,	и	в	этом	случае	он	не	учитывается	при	приведении	к	текстовому	виду.	
Необходимо	создать	следующие	имена:	
• Клеопатра	
• Пушкин	Александр	Сергеевич	
• Маяковский	Владимир	
Обратите	внимание,	что	при	выводе	на	экран,	не	заданные	параметры	никак	не	участвуют	в	
образовании	строки.
### Алгоритм решения
```java
public class Name {
    private String lastName;
    private String firstName;
    private String middleName;
// Конструктор с тремя параметрами
public Name(String lastName, String firstName, String middleName) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.middleName = middleName;
}

// С двумя параметрами
public Name(String lastName, String firstName) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.middleName = null;
}

// С одним параметром
public Name(String firstName) {
    this.firstName = firstName;
    this.lastName = null;
    this.middleName = null;
}
```
Метод toString() формирует строку, проверяя, какие поля заданы:
```java
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    if (lastName != null && !lastName.isEmpty()) sb.append(lastName).append(" ");
    if (firstName != null && !firstName.isEmpty()) sb.append(firstName).append(" ");
    if (middleName != null && !middleName.isEmpty()) sb.append(middleName);
    return sb.toString().trim();
}
```

## Задача 5
### Текст задачи
Создайте	сущность	Дом,	которая	описывается	количеством	этажей	в	виде	числа.	У	Дома	можно	
запросить	текстовую	форму,	которое	имеет	представление	вида	“дом	с	N	этажами”,	где	N	это	
число.	Гарантировать	правильное	окончание	фразы,	в	зависимости	от	количества	этажей.	Создать	
и	вывести	на	экран	дома	с	1,	5,	23	этажами.

### Алгоритм решения
Создан класс House с полем floors:
```java
public class House {
    private int floors;

    public House(int floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        int n = floors % 100;
        String ending;

        if (n >= 11 && n <= 19) {
            ending = "этажами";
        } else {
            int last = n % 10;
            if (last == 1) {
                ending = "этажом";
            } else if (last >= 2 && last <= 4) {
                ending = "этажами";
            } else {
                ending = "этажами";
            }
        }

        return "Дом с " + floors + " " + ending;
    }
}
```
В методе toString() реализована логика правильного окончания: «этажом», «этажами».

# Задание 2
## Задача 4
### Текст задачи
Создайте	сущность	Сотрудник,	которая	описывается	именем	(в	строковой	форме)	и	отделом,	в	
котором	сотрудник	работает,	причем	у	каждого	отдела	есть	название	и	начальник,	который	
также	является	Сотрудником.	Сотрудник	может	быть	приведен	к	текстовой	форме	вида:	“Имя	
работает	в	отделе	Название,	начальник	которого	Имя”.	В	случае	если	сотрудник	является	
руководителем	отдела,	то	текстовая	форма	должна	быть	“Имя	начальник	отдела	Название”.	
Необходимо	выполнить	следующие	задачи:	
1. Создать	Сотрудников	Петрова,	Козлова,	Сидорова	работающих	в	отделе	IT.	
2. Сделать	Козлова	начальником	IT	отдела.	
3. Вывести	на	экран	текстовое	представление	всех	трех	Сотрудников	(у	всех	троих	должен	
оказаться	один	и	тот	же	отдел	и	начальник).
### Алгоритм решения
Создан класс Employee:
```java
public class Employee {
    private String name;
    private Department department;

    public Employee(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
        if (department != null) {
            department.addEmployee(this); // добавляем сотрудника в список
        }
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        if (department == null) return name + " не прикреплён к отделу";

        if (department.getHead() == this) {
            return name + " — начальник отдела " + department.getName();
        } else {
            return name + " работает в отделе " + department.getName() +
                    ", начальник которого " + department.getHead().getName();
        }
    }
}
```
Создан класс Department:
```java
import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private Employee head;
    private List<Employee> employees; // список сотрудников отдела

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public void setHead(Employee head) {
        this.head = head;
        addEmployee(head); // начальник в списке
    }

    public void addEmployee(Employee e) {
        if (!employees.contains(e)) {
            employees.add(e);
        }
    }

    public String getName() {
        return name;
    }

    public Employee getHead() {
        return head;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Отдел " + name + ", начальник: " + (head != null ? head.getName() : "не назначен");
    }
}
```
В Main создаётся отдел, добавляются сотрудники и назначается начальник.

# Задание 3
## Задача 4
### Текст задачи
Измените	решение,	полученное	в	задаче	2.4	таким	образом,	чтобы	имея	ссылку	на	сотрудника,	
можно	было	бы	узнать	список	всех	сотрудников	этого	отдела.
### Алгоритм решения
В классе Department хранится список сотрудников (List<Employee>).

Метод getEmployees() возвращает всех сотрудников отдела.

В Main по фамилии сотрудника определяется его отдел и выводятся все сотрудники:
```java
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
```

# Задание 4
## Задача 5
### Текст задачи
Измените	сущность	Имя	из	задачи	1.3.	Новые	требования	включают:
• Имя можно создать	указав только Личное имя	
• Имя можно	создать	указав Личное имя и	Фамилию.	
• Имя можно	создать	указав все три параметра: Личное	имя,	Фамилию,	Отчество.	
Необходимо	создать	следующие	имена:
1. Клеопатра	
2. Александр	Сергеевич	Пушкин	
3. Владимир	Маяковский	
4. Христофор	Бонифатьевич	(здесь	Христофор	это	имя,	а	Бонифатьевич	-	фамилия)
### Алгоритм решения
Использован уже существующий класс Name из задания 1.

Добавлены конструкторы для всех случаев:
```java
public class Name {
    private String lastName;
    private String firstName;
    private String middleName;

    // Конструктор с 3 параметрами
    public Name(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    // с 2 параметрами
    public Name(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = null;
    }

    // с 1 параметром
    public Name(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (lastName != null && !lastName.isEmpty()) sb.append(lastName).append(" ");
        if (firstName != null && !firstName.isEmpty()) sb.append(firstName).append(" ");
        if (middleName != null && !middleName.isEmpty()) sb.append(middleName);
        return sb.toString().trim();
    }
}
```
Метод toString() выводит только те поля, которые заданы.

# Задание 5
## Задача 5
### Текст задачи
Создайте сущность Дробь	со	следующими	особенностями:	
• Имеет	числитель:	целое	число	
• Имеет	знаменатель:	целое	число	
• Дробь	может	быть	создана	с	указанием	числителя	и	знаменателя		
• Может	вернуть	строковое	представление	вида	“числитель/знаменатель”	
• Может	выполнять	операции	сложения,	вычитания,	умножения	и	деления	с	другой	Дробью	
или	целым	числом.	Результатом	операции	должна	быть	новая	Дробь	(таким	образом,обе	
исходные	дроби	не	изменяются)	
Затем	необходимо	выполнить	следующие	задачи:	
1. Создать	несколько	экземпляров	дробей.	
2. Написать	по	одному	примеру	использования	каждого	метода.	
3. Вывести	на	экран	примеры	и	результаты	их	выполнения	в	формате	«1/3	*	2/3	=	2/9»	
4. Посчитать	f1.sum(f2).div(f3).minus(5)
### Алгоритм решения
Создан класс Fraction:
```java
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
```
В Main реализован ввод дробей с клавиатуры, демонстрация всех операций и вычисление сложного выражения.
