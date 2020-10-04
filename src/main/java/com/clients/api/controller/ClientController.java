package com.clients.api.controller;

import com.clients.api.businessobject.ClientBO;
import com.clients.api.dto.ClientsKpi;
import com.clients.api.exceptions.ApiException;
import com.clients.api.model.Client;
import com.clients.api.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client, BindingResult bindingResult) throws Exception {

        validateBody(bindingResult);

        Client newClient = clientService.createClient(client);

        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @GetMapping("/clientskpi")
    public ResponseEntity<ClientsKpi> getClientsKpi() {
        ClientsKpi clientsKpi = clientService.getClientsKpi();

        return new ResponseEntity<>(clientsKpi, HttpStatus.OK);
    }

    @GetMapping("/clientslist")
    public ResponseEntity<List<ClientBO>> getClientsListWithProbablyDeathDate() {
        List<ClientBO> clientLifeExpectationList = clientService.getClientsListWithProbablyDeathDate();

        return new ResponseEntity<>(clientLifeExpectationList, HttpStatus.OK);
    }

    private void validateBody(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> fieldErrors = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            throw new ApiException("Validation Error", fieldErrors.stream().collect(Collectors.joining(",")), HttpStatus.BAD_REQUEST.value());
        }
    }
}
