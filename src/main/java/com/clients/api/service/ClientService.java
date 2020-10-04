package com.clients.api.service;

import com.clients.api.businessobject.ClientBO;
import com.clients.api.dto.ClientsKpi;
import com.clients.api.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    Client createClient(Client client);

    ClientsKpi getClientsKpi();

    List<ClientBO> getClientsListWithProbablyDeathDate();
}
