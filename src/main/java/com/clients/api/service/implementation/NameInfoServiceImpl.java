package com.clients.api.service.implementation;

import com.clients.api.businessobject.NameProcedenceBO;
import com.clients.api.dto.name.PersonalNames;
import com.clients.api.dto.name.PersonalNamesGender;
import com.clients.api.model.Client;
import com.clients.api.service.NameInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.clients.api.constant.Constants.*;

@Service
public class NameInfoServiceImpl implements NameInfoService {

    private final String xApiKeyValue;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public NameInfoServiceImpl(@Value("${namsor.api.x-api-key}") String xApiKeyValue) {
        this.xApiKeyValue = xApiKeyValue;
    }

    @Override
    public PersonalNames getNamesProcedence(List<Client> clients) {
        HttpHeaders requestHeaders = buildHeaders();

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(buildRequestBody(clients), requestHeaders);

        return restTemplate.postForObject(NAMSOR_API_URL, requestEntity, PersonalNames.class);
    }

    @Override
    public PersonalNamesGender getNamesGender(List<Client> clients) {
        HttpHeaders requestHeaders = buildHeaders();

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(buildRequestBody(clients), requestHeaders);

        return restTemplate.postForObject(NAMSOR_GENDER_API_URL, requestEntity, PersonalNamesGender.class);
    }

    private Map buildRequestBody(List<Client> clients) {
        //ver de enviar surname para nameprocedence ya que es mas exacto
        List<NameProcedenceBO> nameProcedenceBOList = clients.stream().map(client -> new NameProcedenceBO().setId(client.getId())
                .setName(client.getName())).collect(Collectors.toList());

        Map<String, Object> bodyRequest = new HashMap<>();
        bodyRequest.put(PERSONAL_NAMES_BODY_FIELD, nameProcedenceBOList);

        return bodyRequest;
    }

    private HttpHeaders buildHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.add(X_API_KEY_HEADER, xApiKeyValue);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());

        return headers;
    }
}
