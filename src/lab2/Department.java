package lab2;

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
