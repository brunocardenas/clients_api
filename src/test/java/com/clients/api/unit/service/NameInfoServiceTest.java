package com.clients.api.unit.service;

import com.clients.api.dto.country.Country;
import com.clients.api.dto.name.NameGender;
import com.clients.api.dto.name.NameProcedence;
import com.clients.api.dto.name.PersonalNames;
import com.clients.api.dto.name.PersonalNamesGender;
import com.clients.api.mock.Mocks;
import com.clients.api.service.NameInfoService;
import com.clients.api.service.implementation.NameInfoServiceImpl;
import com.github.dockerjava.api.exception.BadRequestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(NameInfoServiceImpl.class)
public class NameInfoServiceTest {

    private RestTemplate restTemplate = mock(RestTemplate.class);

    private BadRequestException badRequestException = mock(BadRequestException.class);

    @InjectMocks
    private NameInfoService nameInfoService = new NameInfoServiceImpl("123test");

    @Test
    public void getNameProcedenceOkTest() throws ParseException {
        NameProcedence nameProcedence = new NameProcedence().setId("123test456")
                .setName("Paolo")
                .setCountry("PT")
                .setCountryAlt("ES");

        PersonalNames personalNamesMock = new PersonalNames()
                .setPersonalNames(Arrays.asList(nameProcedence));

        when(restTemplate.postForObject(any(String.class), any(HttpEntity.class), eq(PersonalNames.class))).thenReturn(personalNamesMock);

        PersonalNames personalNames = nameInfoService.getNamesProcedence(Mocks.getTestClients(false));

        assertEquals("Paolo", personalNames.getPersonalNames().get(0).getName());
    }

    @Test
    public void getNameProcedenceWithExceptionTest() throws Exception {
        NameInfoService nameInfoServiceSpy = PowerMockito.spy(nameInfoService);
        PowerMockito.doReturn(new HttpHeaders()).when(nameInfoServiceSpy, "buildHeaders");
        PowerMockito.doReturn(new HashMap<>()).when(nameInfoServiceSpy, "buildRequestBody", any(List.class));

        when(restTemplate.postForObject(any(String.class), any(HttpEntity.class), eq(PersonalNames.class))).thenThrow(badRequestException);

        assertThrows(BadRequestException.class, () -> this.nameInfoService.getNamesProcedence(Mocks.getTestClients(false)));
    }

    @Test
    public void getNameGenderOkTest() throws ParseException {
        NameGender nameGender = new NameGender().setId("123test456")
                .setName("Paolo")
                .setLikelyGender("Male");

        PersonalNamesGender personalNamesGenderMock = new PersonalNamesGender()
                .setPersonalNames(Arrays.asList(nameGender));

        when(restTemplate.postForObject(any(String.class), any(HttpEntity.class), eq(PersonalNamesGender.class))).thenReturn(personalNamesGenderMock);

        PersonalNamesGender personalNamesGender = nameInfoService.getNamesGender(Mocks.getTestClients(false));

        assertEquals("Paolo", personalNamesGender.getPersonalNames().get(0).getName());
    }

    @Test
    public void getNameGenderWithExceptionTest() throws Exception {
        NameInfoService nameInfoServiceSpy = PowerMockito.spy(nameInfoService);
        PowerMockito.doReturn(new HttpHeaders()).when(nameInfoServiceSpy, "buildHeaders");
        PowerMockito.doReturn(new HashMap<>()).when(nameInfoServiceSpy, "buildRequestBody", any(List.class));

        when(restTemplate.postForObject(any(String.class), any(HttpEntity.class), eq(PersonalNamesGender.class))).thenThrow(badRequestException);


        assertThrows(BadRequestException.class, () -> this.nameInfoService.getNamesGender(Mocks.getTestClients(false)));
    }

}
