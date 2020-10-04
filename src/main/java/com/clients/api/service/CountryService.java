package com.clients.api.service;

import com.clients.api.dto.country.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {

    List<Country> getCountryName(List<String> countryAlphaCode);
}
