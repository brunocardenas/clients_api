package com.clients.api.service;

import com.clients.api.dto.gho.GhoLifeExpectancy;
import org.springframework.stereotype.Service;

@Service
public interface GhoService {

    GhoLifeExpectancy getLifeExpectancyInfo();
}
