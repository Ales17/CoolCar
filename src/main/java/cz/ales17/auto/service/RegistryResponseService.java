package cz.ales17.auto.service;

import cz.ales17.auto.dto.ApiResponseData;
import cz.ales17.auto.entity.ApiCall;

import java.time.Duration;
import java.util.Optional;

public interface RegistryResponseService {
    ApiCall saveApiCall(ApiResponseData data, String vin);

    Optional<ApiCall> findRecent(String vin, Duration maxAge);
}
