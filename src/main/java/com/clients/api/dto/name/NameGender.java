package com.clients.api.dto.name;

public class NameGender {

    private String id;
    private String name;
    private String likelyGender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLikelyGender() {
        return likelyGender;
    }

    public void setLikelyGender(String likelyGender) {
        this.likelyGender = likelyGender;
    }
}
