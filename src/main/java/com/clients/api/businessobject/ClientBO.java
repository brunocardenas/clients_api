package com.clients.api.businessobject;

import java.util.Date;

public class ClientBO {

    private String id;
    private String name;
    private String surname;
    private Integer age;
    private Date birthDate;
    private Date probablyDeathDate;

    public String getId() {
        return id;
    }

    public ClientBO withId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClientBO withName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public ClientBO withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public ClientBO withAge(Integer age) {
        this.age = age;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public ClientBO withBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Date getProbablyDeathDate() {
        return probablyDeathDate;
    }

    public ClientBO withProbablyDeathDate(Date probablyDeathDate) {
        this.probablyDeathDate = probablyDeathDate;
        return this;
    }
}
