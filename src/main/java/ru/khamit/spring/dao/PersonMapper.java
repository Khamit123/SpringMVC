package ru.khamit.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.khamit.spring.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person=new Person();
        person.setName(rs.getString("name"));
        person.setEmail(rs.getString("email"));
        person.setId(rs.getInt("person_id"));
        return person;
    }
}
