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

    public NameProcedence setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public NameProcedence setName(String name) {
        this.name = name;
        return this;
    }

    public Double getScore() {
        return score;
    }

    public NameProcedence setScore(Double score) {
        this.score = score;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public NameProcedence setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCountryAlt() {
        return countryAlt;
    }

    public NameProcedence setCountryAlt(String countryAlt) {
        this.countryAlt = countryAlt;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public NameProcedence setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getTopRegion() {
        return topRegion;
    }

    public NameProcedence setTopRegion(String topRegion) {
        this.topRegion = topRegion;
        return this;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public NameProcedence setSubRegion(String subRegion) {
        this.subRegion = subRegion;
        return this;
    }

    public List<String> getCountriesTop() {
        return countriesTop;
    }

    public NameProcedence setCountriesTop(List<String> countriesTop) {
        this.countriesTop = countriesTop;
        return this;
    }

    public Double getProbabilityCalibrated() {
        return probabilityCalibrated;
    }

    public NameProcedence setProbabilityCalibrated(Double probabilityCalibrated) {
        this.probabilityCalibrated = probabilityCalibrated;
        return this;
    }

    public Double getProbabilityAltCalibrated() {
        return probabilityAltCalibrated;
    }

    public NameProcedence setProbabilityAltCalibrated(Double probabilityAltCalibrated) {
        this.probabilityAltCalibrated = probabilityAltCalibrated;
        return this;
    }
}
