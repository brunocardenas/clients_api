package com.clients.api.service;

import com.clients.api.dto.country.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {

    /**
     * This method allow user to obtain the name of a country based
     * on its alphaCode abbreviation.
     * @param countryAlphaCode the alpha code of the country that we want the information.
     * @return the country/countries that user requests.
     */
    List<Country> getCountryName(List<String> countryAlphaCode);
}
