package com.clients.api.util;

import com.clients.api.dto.ClientsKpi;
import com.clients.api.model.Client;

import java.util.List;
import java.util.stream.Collectors;

public class ClientUtils {


    public static ClientsKpi buildClientsKpiResponse(List<Client> clientList) {
        List<Integer> clientsAges = getListOfClientsAges(clientList);
        double averageAge = calculateAverageAge(clientsAges);
        Double standardDeviation = calculateStandardDeviation(averageAge, clientsAges);

        return new ClientsKpi().withAverageAge(averageAge)
                .withStandardDeviation(standardDeviation);
    }


    private static double calculateAverageAge(List<Integer> clientsAges) {

        return clientsAges.stream()
                .mapToDouble(Number::doubleValue)
                .summaryStatistics()
                .getAverage();
    }

    private static Double calculateStandardDeviation(Double averageAge, List<Integer> clientsAges) {

        Double previousSum = clientsAges.stream()
                .mapToDouble((age) -> Math.pow(age.doubleValue() - averageAge, 2.0))
                .sum();

        return Math.sqrt(previousSum / clientsAges.size());
    }

    private static List<Integer> getListOfClientsAges(List<Client> clientList) {

        return clientList.stream().map(Client::getAge).collect(Collectors.toList());
    }
}
