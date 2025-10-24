package lab2;

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