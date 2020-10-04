package com.clients.api.unit.service;

import com.clients.api.dto.gho.GhoLifeExpectancy;
import com.clients.api.mock.Mocks;
import com.clients.api.service.GhoService;
import com.clients.api.service.implementation.GhoServiceImpl;
import com.github.dockerjava.api.exception.BadRequestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GhoServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private BadRequestException badRequestException;

    @InjectMocks
    private GhoService ghoService = new GhoServiceImpl();

    @Test
    public void getLifeExpectancyInfoOk() {
        GhoLifeExpectancy ghoLifeExpectancyMock = Mocks.getLifeExpectancyMock();

        when(restTemplate.getForObject(any(String.class), eq(GhoLifeExpectancy.class))).thenReturn(ghoLifeExpectancyMock);


        GhoLifeExpectancy ghoLifeExpectancyResponse = ghoService.getLifeExpectancyInfo();
        assertEquals(ghoLifeExpectancyMock.getFact().size(), ghoLifeExpectancyResponse.getFact().size());
    }

    @Test
    public void getLifeExpectancyInfoWithException() {
        when(restTemplate.getForObject(any(String.class), eq(GhoLifeExpectancy.class))).thenThrow(badRequestException);
        assertThrows(BadRequestException.class, () -> this.ghoService.getLifeExpectancyInfo());
    }

}
