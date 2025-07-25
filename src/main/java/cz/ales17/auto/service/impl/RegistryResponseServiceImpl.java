package cz.ales17.auto.service.impl;

import cz.ales17.auto.dto.ApiResponseData;
import cz.ales17.auto.entity.ApiCall;
import cz.ales17.auto.entity.Car;
import cz.ales17.auto.repository.ApiCallRepository;
import cz.ales17.auto.repository.CarRepository;
import cz.ales17.auto.service.RegistryResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistryResponseServiceImpl implements RegistryResponseService {
    private final ApiCallRepository apiCallRepository;
    private final CarRepository carRepository;

    @Override
    public void saveApiCall(ApiResponseData data, String vin) {
        Car vehicle = carRepository.findByVinCode(vin);
        ApiCall apiCall = new ApiCall();
        apiCall.setVehicle(vehicle);
        apiCall.setResponseData(data);
        apiCallRepository.save(apiCall);
    }

    public ApiResponseData mapToResponseData(ApiCall apiCall) {
        return apiCall.getResponseData();
    }
    @Override
    public Optional<ApiResponseData> findRecent(String vin, Duration maxAge) {
        LocalDateTime threshold = LocalDateTime.now().minus(maxAge);
        return apiCallRepository
                .findTopByVehicle_VinCodeAndCreatedOnAfterOrderByCreatedOnDesc(vin, threshold)
                .map(this::mapToResponseData);
    }



}
