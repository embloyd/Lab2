package lab2;

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

