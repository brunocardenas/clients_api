package com.clients.api.dto.gho;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GhoLifeExpectancy {

    @JsonProperty("fact")
    private List<Fact> fact;

    public List<Fact> getFact() {
        return fact;
    }

    public void setFact(List<Fact> fact) {
        this.fact = fact;
    }
}
