package com.clients.api.service;

import com.clients.api.dto.name.PersonalNames;
import com.clients.api.dto.name.PersonalNamesGender;
import com.clients.api.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NameInfoService {

    PersonalNames getNamesProcedence(List<Client> clients);

    PersonalNamesGender getNamesGender(List<Client> clients);
}
