package ru.khamit.spring.dao;

import org.springframework.stereotype.Component;
import ru.khamit.spring.model.Person;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDao {
    public static int PORT=5432;
    public static final String URL="jdbc:postgresql://localhost:5432/spring_practice";
    public static final String USER="postgres";
    public static final String PASSWORD="root";
    private Connection connection;
    private Statement statement;

    private static  int COUNT=0;

   static  {

    }
    @PostConstruct
    private void init(){
        try {
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection(URL,USER,PASSWORD);
           statement=connection.createStatement();

        }
        catch (SQLException E){

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
    @PreDestroy
    private void destroy(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void newPerson(Person person){
        try {
            statement.executeUpdate("INSERT INTO person (name,email)" +
                    " values ('"+person.getName() +"','"+person.getEmail()+"');");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//    public void deletePerson(Person person){
//        people.remove(person);
//    }
//    public void update(int id,Person person){
//        Person personToBeUpdated = person(id);
//        personToBeUpdated.setName(person.getName());
//    }
//    public void delete(int id){
//        people.removeIf(person -> person.getId()==id);
//    }

    public List<Person> People() {
        List<Person> people =new ArrayList<>();
        try {
            String sql="SELECT * FROM person";
           ResultSet rs= statement.executeQuery(sql);
            while(rs.next()){
               int id= rs.getInt("person_id");
                String name =rs.getString("name");
                String email = rs.getString("email");
                Person p=new Person(id,name,email);
                people.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return people;
    }
//    public Person person(int id) {
//        return people.stream().filter(person -> person.getId()==id).findAny().orElse(null);
//    }
}
