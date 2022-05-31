public class Person {

    private final String name;
    private final String surname;
    private String phone;
    Person(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }
}
