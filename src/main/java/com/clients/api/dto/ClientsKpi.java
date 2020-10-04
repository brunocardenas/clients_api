package com.clients.api.dto;

public class ClientsKpi {

    private Double averageAge;
    private Double standardDeviation;

    public Double getAverageAge() {
        return averageAge;
    }

    public ClientsKpi withAverageAge(Double averageAge) {
        this.averageAge = averageAge;
        return this;
    }

    public Double getStandardDeviation() {
        return standardDeviation;
    }

    public ClientsKpi withStandardDeviation(Double standardDeviation) {
        this.standardDeviation = standardDeviation;
        return this;
    }
}
