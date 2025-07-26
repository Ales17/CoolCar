package cz.ales17.auto.service;

import cz.ales17.auto.entity.ApiCall;

public interface VehicleDataProvider {
    ApiCall getVehicleData(String vin) throws Exception;
}
