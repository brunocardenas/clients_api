package com.clients.api.service.implementation;

import com.clients.api.dto.gho.GhoLifeExpectancy;
import com.clients.api.service.GhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GhoServiceImpl implements GhoService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public GhoLifeExpectancy getLifeExpectancyInfo() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("apps.who.int")
                .path("/gho/athena/api/GHO/WHOSIS_000001.json").query("profile={keyword}").buildAndExpand("simple");

        return restTemplate.getForObject(uriComponents.toUriString(), GhoLifeExpectancy.class);
    }
}
