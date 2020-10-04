package com.clients.api.dto.gho;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fact {

    private Dim dim;
    @JsonProperty("Comments")
    private String comments;
    @JsonProperty("Value")
    private String value;

    public Dim getDim() {
        return dim;
    }

    public void setDim(Dim dim) {
        this.dim = dim;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
