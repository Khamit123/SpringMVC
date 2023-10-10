package ru.khamit.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.khamit.spring.model.Person;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PeopleDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


    public void newPerson(Person person){
        jdbcTemplate.update("INSERT INTO  person (name,email)" +
                " values (?,?)",person.getName(),person.getEmail());
    }
    public void deletePerson(Person person){
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?",person.getId());
    }
    public void update(int id,Person person){
        jdbcTemplate.update("UPDATE person SET name=?,email=? WHERE person_id=?",person.getName(),person.getEmail(),id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?",id);
    }

    public List<Person> People() {
//        List<Person> people =new ArrayList<>();
//        try {
//            String sql="SELECT * FROM person";
//           ResultSet rs= statement.executeQuery(sql);
//            while(rs.next()){
//               int id= rs.getInt("person_id");
//                String name =rs.getString("name");
//                String email = rs.getString("email");
//                Person p=new Person(id,name,email);
//                people.add(p);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        return jdbcTemplate.query("SELECT * FROM person",new PersonMapper());//new BeanPropertyRowMapper<>(Person.class)
    }
    public Person person(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?",
                new Object[]{id},new PersonMapper()).stream().findAny().orElse(null);
    }
}
