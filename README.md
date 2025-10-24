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

@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    if (lastName != null && !lastName.isEmpty()) sb.append(lastName).append(" ");
    if (firstName != null && !firstName.isEmpty()) sb.append(firstName).append(" ");
    if (middleName != null && !middleName.isEmpty()) sb.append(middleName);
    return sb.toString().trim();
}
