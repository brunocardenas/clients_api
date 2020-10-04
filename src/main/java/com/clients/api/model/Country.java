package com.clients.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lifeexpectancy")
public class Country {

    @Id
    private Long id;
    private String country;
    private Double expectancyYears;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getExpectancyYears() {
        return expectancyYears;
    }

    public void setExpectancyYears(Double expectancyYears) {
        this.expectancyYears = expectancyYears;
    }
}
