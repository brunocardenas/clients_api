package com.clients.api.util;

import com.clients.api.businessobject.ClientBO;
import com.clients.api.dto.country.Country;
import com.clients.api.dto.gho.Fact;
import com.clients.api.dto.gho.GhoLifeExpectancy;
import com.clients.api.dto.name.NameGender;
import com.clients.api.dto.name.NameProcedence;
import com.clients.api.model.Client;
import com.clients.api.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class LifeExpectanceUtils {

    @Autowired
    CountryService countryService;

    public static List<ClientBO> calculateProbablyDeathDates(List<NameProcedence> nameProcedenceList, List<Client> clientsList, GhoLifeExpectancy ghoLifeExpectancyData, List<Country> countriesWithAbbreviation, List<NameGender> nameGenderList) {
        List<ClientBO> clientBOList = new ArrayList<>();
        List<NameProcedence> sortedNameProcedenceList = sortNameProcedenceList(nameProcedenceList);
        List<Client> sortedClientList = sortClientList(clientsList);

        for (NameProcedence nameProcedence : sortedNameProcedenceList) {

            Country originCountry = countriesWithAbbreviation.stream()
                    .filter(country -> country.getAlpha2Code().equals(nameProcedence.getCountry()))
                    .findAny().orElse(null);

            List<Fact> noNullCountryFactsData = ghoLifeExpectancyData.getFact().stream()
                .filter(fact -> fact.getDim().getCountry() != null)
                    .collect(Collectors.toList());

            List<Fact> countryFactsData = noNullCountryFactsData.stream()
                    .filter(fact -> fact.getDim().getCountry().equals(originCountry.getName()))
                    .collect(Collectors.toList());

            NameGender nameGender = nameGenderList.stream()
                    .filter(name -> name.getId().equals(nameProcedence.getId()))
                    .findAny().orElse(null);

            List<Fact> nameGenderInfo = countryFactsData.stream()
                    .filter(fact -> fact.getDim().getSex().equalsIgnoreCase(nameGender.getLikelyGender()))
                    .collect(Collectors.toList());

            Fact lastYearInfo = nameGenderInfo.stream().max(Comparator.comparing(fact -> fact.getDim().getYear())).get();

            Client client = sortedClientList.stream().filter(actualClient -> actualClient.getId().equals(nameProcedence.getId()))
                    .findFirst().orElse(null);

            ClientBO clientBO = new ClientBO().withId(client.getId())
                    .withName(client.getName())
                    .withSurname(client.getSurname())
                    .withAge(client.getAge())
                    .withBirthDate(client.getBirthDate())
                    .withProbablyDeathDate(calculateProbablyDeathDate(lastYearInfo.getValue(), client.getBirthDate()));

            clientBOList.add(clientBO);
        }

        return clientBOList;
    }

    private static Date calculateProbablyDeathDate(String lifeExpectancy, Date birthDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(birthDate);

        LocalDate probablyDeathDate = LocalDate.parse(sdf.format(birthDate)).plusYears(Math.round(Double.parseDouble(lifeExpectancy)));

        return Date.from(probablyDeathDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static List<String> getCountriesOfProcedence(List<NameProcedence> nameProcedenceList) {

        return nameProcedenceList.stream().map(NameProcedence::getCountry).collect(Collectors.toList());
    }

    public static List<NameProcedence> sortNameProcedenceList(List<NameProcedence> nameProcedenceList) {
        return nameProcedenceList.stream()
                .sorted(Comparator.comparing(NameProcedence::getId))
                .collect(Collectors.toList());
    }

    public static List<Client> sortClientList(List<Client> clientsList) {
        return clientsList.stream()
                .sorted(Comparator.comparing(Client::getId))
                .collect(Collectors.toList());
    }
}
