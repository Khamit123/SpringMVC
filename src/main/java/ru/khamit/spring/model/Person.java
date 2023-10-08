package ru.khamit.spring.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "Name cant be empty")
    @Size(min=2,max=30,message = "Name must have between 2 adn 30 characters")
    private String name;
    @Email(message = "It is not email")
    @NotEmpty(message = "Email cant be empty")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int id, String name,String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Person(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
