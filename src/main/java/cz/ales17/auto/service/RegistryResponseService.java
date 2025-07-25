package cz.ales17.auto.service;

import cz.ales17.auto.dto.ApiResponseData;

import java.time.Duration;
import java.util.Optional;

public interface RegistryResponseService {
    void saveApiCall(ApiResponseData data, String vin);

    Optional<ApiResponseData> findRecent(String vin, Duration maxAge);
}
