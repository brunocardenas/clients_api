package com.clients.api.dto.name;

import java.util.List;

public class NameProcedence {

    private String id;
    private String name;
    private Double score;
    private String country;
    private String countryAlt;
    private String region;
    private String topRegion;
    private String subRegion;
    private List<String> countriesTop;
    private Double probabilityCalibrated;
    private Double probabilityAltCalibrated;

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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryAlt() {
        return countryAlt;
    }

    public void setCountryAlt(String countryAlt) {
        this.countryAlt = countryAlt;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTopRegion() {
        return topRegion;
    }

    public void setTopRegion(String topRegion) {
        this.topRegion = topRegion;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public List<String> getCountriesTop() {
        return countriesTop;
    }

    public void setCountriesTop(List<String> countriesTop) {
        this.countriesTop = countriesTop;
    }

    public Double getProbabilityCalibrated() {
        return probabilityCalibrated;
    }

    public void setProbabilityCalibrated(Double probabilityCalibrated) {
        this.probabilityCalibrated = probabilityCalibrated;
    }

    public Double getProbabilityAltCalibrated() {
        return probabilityAltCalibrated;
    }

    public void setProbabilityAltCalibrated(Double probabilityAltCalibrated) {
        this.probabilityAltCalibrated = probabilityAltCalibrated;
    }
}
