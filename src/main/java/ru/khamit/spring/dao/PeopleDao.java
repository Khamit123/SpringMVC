package ru.khamit.spring.dao;

import org.springframework.stereotype.Component;
import ru.khamit.spring.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDao {
    private static  int COUNT=0;
    private List<Person> people;
    {
        people=new ArrayList<>();
        people.add(new Person(++COUNT,"Airat"));
        people.add(new Person(++COUNT,"Khamit"));
        people.add(new Person(++COUNT,"Kamil"));
        people.add(new Person(++COUNT,"Max"));
        people.add(new Person(++COUNT,"Masha"));
    }
    public void newPerson(Person person){
        people.add(new Person(++COUNT, person.getName()));
    }
    public void deletePerson(Person person){
        people.remove(person);
    }

    public List<Person> People() {
        return people;
    }
    public Person person(int id) {
        return people.stream().filter(person -> person.getId()==id).findAny().orElse(null);
    }
}
