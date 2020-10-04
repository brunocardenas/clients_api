package com.clients.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "client")
public class Client {

    @Id
    private String id;

    @NotNull(message = "Client name is required")
    private String name;

    @NotNull(message = "Client surname is required")
    private String surname;

    @NotNull(message = "Client age is required")
    private Integer age;

    @NotNull(message = "Birth date is required and should be in the format YYYY-MM-DD")
    private Date birthDate;

    public String getId() {
        return id;
    }

    public Client setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Client setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Client setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Client setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
