package restAPI.serivce;

import org.springframework.stereotype.Service;
import restAPI.Model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class Filter {

    private List<Person> persons = new ArrayList<>();


    public List<Person> filter(String name, String surname, String phone) {

        if (name != null) {
            return persons.stream()
                    .filter(person -> person.getName().equals(name))
                    .collect(Collectors.toList());
        }
        if (surname != null) {
            return persons.stream()
                    .filter(person -> person.getSurname().equals(surname))
                    .collect(Collectors.toList());
        }
        if (phone != null) {

            return persons.stream()
                    .filter(person -> person.getPhone().equals(phone))
                    .collect(Collectors.toList());
        }
        return persons;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
