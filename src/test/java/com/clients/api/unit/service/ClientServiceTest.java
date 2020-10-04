package com.clients.api.unit.service;

import com.clients.api.businessobject.ClientBO;
import com.clients.api.dto.ClientsKpi;
import com.clients.api.dto.country.Country;
import com.clients.api.dto.gho.GhoLifeExpectancy;
import com.clients.api.dto.name.NameGender;
import com.clients.api.dto.name.NameProcedence;
import com.clients.api.dto.name.PersonalNames;
import com.clients.api.dto.name.PersonalNamesGender;
import com.clients.api.exceptions.ApiException;
import com.clients.api.mock.Mocks;
import com.clients.api.model.Client;
import com.clients.api.repository.ClientRepository;
import com.clients.api.service.ClientService;
import com.clients.api.service.CountryService;
import com.clients.api.service.GhoService;
import com.clients.api.service.NameInfoService;
import com.clients.api.service.implementation.ClientServiceImpl;
import com.clients.api.util.ClientUtils;
import com.clients.api.util.LifeExpectanceUtils;
import com.github.dockerjava.api.exception.BadRequestException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ClientUtils.class, LifeExpectanceUtils.class})
public class ClientServiceTest {

    private ClientRepository clientRepository = mock(ClientRepository.class);
    private NameInfoService nameInfoService = mock(NameInfoService.class);
    private GhoService ghoService = mock(GhoService.class);
    private CountryService countryService = mock(CountryService.class);
    private BadRequestException badRequestException = mock(BadRequestException.class);

    @InjectMocks
    ClientService clientService = new ClientServiceImpl();

    @Before
    public void setUp() {
        mockStatic(ClientUtils.class);
        mockStatic(LifeExpectanceUtils.class);
    }

    @Test
    public void createClientOkTest() throws ParseException {
        List<Client> clients = Mocks.getTestClients(false);
        when(clientRepository.save(any(Client.class))).thenReturn(clients.get(0));

        Client savedClient = clientService.createClient(clients.get(0));

        assertEquals(clients.get(0).getName(), savedClient.getName());
    }

    @Test
    public void getClientsKpiOkTest() throws ParseException {
        List<Client> clientsForTest = Mocks.getTestClients(false);
        when(clientRepository.findAll()).thenReturn(clientsForTest);
        when(ClientUtils.buildClientsKpiResponse(any(List.class))).thenReturn(Mocks.getClientsKpiMock());

        ClientsKpi clientsKpiResponse = clientService.getClientsKpi();
        assertEquals(Double.valueOf(68.5), clientsKpiResponse.getAverageAge());
        assertEquals(Double.valueOf(4.2), clientsKpiResponse.getStandardDeviation());
    }

    @Test
    public void getClientsKpiWithExceptionTest() {
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ApiException.class, () -> this.clientService.getClientsKpi());
    }

    @Test
    public void getClientsListWithProbablyDeathDateTest() {
        NameProcedence nameProcedence = Mocks.getNameProcedenceObject();
        PersonalNames personalNames = Mocks.getPersonalNamesObject(nameProcedence);
        NameGender nameGender = Mocks.getNameGenederObject();
        Country country = Mocks.getCountryObject();
        PersonalNamesGender personalNamesGender = Mocks.getPersonalNamesGenderObject(nameGender);

        List<ClientBO> expectedList = Mocks.getClientsWithDeathDateUtilsMock();

        when(nameInfoService.getNamesProcedence(any(List.class))).thenReturn(personalNames);
        when(nameInfoService.getNamesGender(any(List.class))).thenReturn(personalNamesGender);
        when(ghoService.getLifeExpectancyInfo()).thenReturn(Mocks.getLifeExpectancyMock());
        when(countryService.getCountryName(any(List.class))).thenReturn(Collections.singletonList(country));

        when(LifeExpectanceUtils.calculateProbablyDeathDates(any(List.class), any(List.class), any(GhoLifeExpectancy.class), any(List.class), any(List.class))).thenReturn(expectedList);

        List<ClientBO> clients = clientService.getClientsListWithProbablyDeathDate();

        assertEquals(expectedList.size(), clients.size());
    }

    @Test
    public void getClientsListWithProbablyDeathDateWithExceptionTest() {
        when(nameInfoService.getNamesProcedence(any(List.class))).thenThrow(badRequestException);

        assertThrows(BadRequestException.class, () -> this.clientService.getClientsListWithProbablyDeathDate());
    }
}
