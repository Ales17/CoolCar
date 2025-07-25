package cz.ales17.auto.service.impl;

import cz.ales17.auto.dto.ApiResponseData;
import cz.ales17.auto.service.ApiClient;
import cz.ales17.auto.service.RegistryResponseService;
import cz.ales17.auto.service.VehicleDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class VehicleDataProviderImpl implements VehicleDataProvider {
    private final RegistryResponseService responseService;
    private final ApiClient apiClient;
    @Override
    public ApiResponseData getVehicleData(String vin) throws Exception {
        Optional<ApiResponseData> cached = responseService.findRecent(vin, Duration.ofHours(1));
        if (cached.isPresent()) {
            System.out.println("Found recent vehicle data. I will not call the API.");
            return cached.get();
        }
        ApiResponseData fresh = apiClient.fetchFromRegistry(vin);
        responseService.saveApiCall(fresh, vin);
        return fresh;
    }
}
