package cz.ales17.auto.service;

import cz.ales17.auto.dto.VehicleInspectionDto;
import cz.ales17.auto.entity.VehicleInspection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VehicleInspectionService {
    List<VehicleInspection> findByCarId(Long carId);
    List<VehicleInspectionDto> findByVehicleId(Long vehicleId);
    void addInspection(VehicleInspection vehicleInspection);
    void deleteInspectionById(Long id);
    VehicleInspectionDto findInspectionById(Long id);
    Page<VehicleInspectionDto> findByVehicleIdPaginated(Long carId, int page, int size);
}
