package com.clients.api.service.implementation;

import com.clients.api.businessobject.ClientBO;
import com.clients.api.dto.ClientsKpi;
import com.clients.api.dto.name.NameGender;
import com.clients.api.dto.name.NameProcedence;
import com.clients.api.dto.country.Country;
import com.clients.api.dto.gho.GhoLifeExpectancy;
import com.clients.api.exceptions.ApiException;
import com.clients.api.model.Client;
import com.clients.api.repository.ClientRepository;
import com.clients.api.service.ClientService;
import com.clients.api.service.CountryService;
import com.clients.api.service.GhoService;
import com.clients.api.service.NameInfoService;
import com.clients.api.util.ClientUtils;
import com.clients.api.util.LifeExpectanceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    NameInfoService nameInfoService;

    @Autowired
    GhoService ghoService;

    @Autowired
    CountryService countryService;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public ClientsKpi getClientsKpi() {
        List<Client> clientList = clientRepository.findAll();

        if (clientList.isEmpty()) {
            throw new ApiException("validation_error", "Database is empty!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return ClientUtils.buildClientsKpiResponse(clientRepository.findAll());
    }

    @Override
    public List<ClientBO> getClientsListWithProbablyDeathDate() {
        List<NameProcedence> nameProcedenceList = nameInfoService.getNamesProcedence(clientRepository.findAll()).getPersonalNames();
        List<NameGender> nameGenderList = nameInfoService.getNamesGender(clientRepository.findAll()).getPersonalNames();
        GhoLifeExpectancy ghoLifeExpectancyInfo = ghoService.getLifeExpectancyInfo();
        List<Country> countriesWithAbbreviation = countryService.getCountryName(LifeExpectanceUtils.getCountriesOfProcedence(nameProcedenceList));

       return LifeExpectanceUtils.calculateProbablyDeathDates(nameProcedenceList, clientRepository.findAll(), ghoLifeExpectancyInfo, countriesWithAbbreviation, nameGenderList);
    }
}
