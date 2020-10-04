package com.clients.api.unit.service;

import com.clients.api.dto.country.Country;
import com.clients.api.service.CountryService;
import com.clients.api.service.implementation.CountryServiceImpl;
import com.github.dockerjava.api.exception.BadRequestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private BadRequestException badRequestException;

    @InjectMocks
    private CountryService countryService = new CountryServiceImpl();

    @Test
    public void getCountryNameOkTest() {
        Country[] countries = {
                new Country()
                        .setName("Spain")
                        .setAlpha2Code("SP")};

        when(restTemplate.getForEntity(any(String.class), eq(Country[].class)))
                .thenReturn(new ResponseEntity<>(countries, HttpStatus.OK));

        List<Country> countryList = countryService.getCountryName(Arrays.asList("SP"));

        assertEquals("Spain", countryList.get(0).getName());
    }

    @Test
    public void getCountryNameWithExceptionTest() {
        when(restTemplate.getForEntity(any(String.class), eq(Country[].class)))
                .thenThrow(badRequestException);

        assertThrows(BadRequestException.class, () -> this.countryService.getCountryName(Arrays.asList("SP")));
    }
}
