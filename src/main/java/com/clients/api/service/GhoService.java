package com.clients.api.service;

import com.clients.api.dto.gho.GhoLifeExpectancy;
import org.springframework.stereotype.Service;

@Service
public interface GhoService {

    /**
     * This method allows user to obtain the life expectancy
     * information from every countries according global healthy
     * organization api. Unfortunately, this api doesn't allow us
     * to retrieve just certain information through query parameters.
     * @return life expectancy information of world countries.
     */
    GhoLifeExpectancy getLifeExpectancyInfo();
}
