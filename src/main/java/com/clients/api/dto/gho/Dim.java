package com.clients.api.dto.gho;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dim {

    @JsonProperty("PUBLISHSTATE")
    private String publishstate;
    @JsonProperty("COUNTRY")
    private String country;
    @JsonProperty("YEAR")
    private String year;
    @JsonProperty("REGION")
    private String region;
    @JsonProperty("SEX")
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPublishstate() {
        return publishstate;
    }

    public void setPublishstate(String publishstate) {
        this.publishstate = publishstate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
