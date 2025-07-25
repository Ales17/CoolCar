package cz.ales17.auto.service;

import cz.ales17.auto.dto.ApiResponseData;

public interface VehicleDataProvider {
    ApiResponseData getVehicleData(String vin) throws Exception;
}
