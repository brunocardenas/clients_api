package com.clients.api.service;

import com.clients.api.dto.name.PersonalNames;
import com.clients.api.dto.name.PersonalNamesGender;
import com.clients.api.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NameInfoService {

    /**
     * This method allows user to obtain procedence info about
     * the client/clients name/s.
     * @param clients the client or clients that we want the procedence information.
     * @return list of clients requested with procedence information.
     */
    PersonalNames getNamesProcedence(List<Client> clients);

    /**
     * This method allows user to obtain gender info about
     * the client/clients name/s.
     * @param clients the client or clients that we want the gender information.
     * @return list of clientes with gender information.
     */
    PersonalNamesGender getNamesGender(List<Client> clients);
}
