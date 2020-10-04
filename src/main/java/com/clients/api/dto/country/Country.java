package com.clients.api.dto.country;

public class Country {

    private String name;
    private String alpha2Code;

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public Country setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
        return this;
    }

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }
}
