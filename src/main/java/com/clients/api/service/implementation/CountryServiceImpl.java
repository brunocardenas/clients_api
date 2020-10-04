package com.clients.api.service.implementation;

import com.clients.api.dto.country.Country;
import com.clients.api.exceptions.ApiException;
import com.clients.api.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.clients.api.constant.Constants.COUNTRY_API_URL;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Country> getCountryName(List<String> countryAlphaCodes) {
        String alphacodes = countryAlphaCodes.stream().collect(Collectors.joining(";"));

        //armar todas las url con el mismo component builder.
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(COUNTRY_API_URL)
                .queryParam("codes", alphacodes);

        ResponseEntity<Country[]> responseEntity = restTemplate.getForEntity(builder.toUriString(), Country[].class);

        return Arrays.asList(responseEntity.getBody());
    }
}
