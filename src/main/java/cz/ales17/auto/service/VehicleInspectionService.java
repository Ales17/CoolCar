package cz.ales17.auto.service;

import cz.ales17.auto.dto.VehicleInspectionDto;
import cz.ales17.auto.entity.VehicleInspection;

import java.util.List;

public interface VehicleInspectionService {
    List<VehicleInspection> findByCarId(Long carId);
    void addInspection(VehicleInspection vehicleInspection);
    void deleteInspectionById(Long id);
    VehicleInspectionDto findInspectionById(Long id);
}
