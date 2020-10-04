package com.clients.api.service;

import com.clients.api.businessobject.ClientBO;
import com.clients.api.dto.ClientsKpi;
import com.clients.api.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    /**
     * This method allow user to create a new client on the database.
     * @param client the client to be created.
     * @return the created client.
     */
    Client createClient(Client client);


    /**
     * This method allow user to obtain the average age and the
     * standard deviation of clients in the database.
     * @return average age and standard deviation data.
     */
    ClientsKpi getClientsKpi();


    /**
     * This method allow user to obtain the list of clients
     * in the database plus a new field with the probably
     * death date of the client.
     * @return clients list with probably death date for each one.
     */
    List<ClientBO> getClientsListWithProbablyDeathDate();
}
