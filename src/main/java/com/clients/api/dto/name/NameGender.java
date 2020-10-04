package com.clients.api.dto.name;

public class NameGender {

    private String id;
    private String name;
    private String likelyGender;

    public String getId() {
        return id;
    }

    public NameGender setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public NameGender setName(String name) {
        this.name = name;
        return this;
    }

    public String getLikelyGender() {
        return likelyGender;
    }

    public NameGender setLikelyGender(String likelyGender) {
        this.likelyGender = likelyGender;
        return this;
    }
}
