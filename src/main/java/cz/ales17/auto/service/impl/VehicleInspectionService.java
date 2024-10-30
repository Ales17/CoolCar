package cz.ales17.auto.service.impl;

import cz.ales17.auto.entity.VehicleInspection;
import cz.ales17.auto.repository.VehicleInspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehicleInspectionService implements cz.ales17.auto.service.VehicleInspectionService {
    @Autowired
    private VehicleInspectionRepository vehicleInspectionRepository;
    @Override
    public List<VehicleInspection> findByCarId(Long carId) {
        return vehicleInspectionRepository.findAllByVehicle_IdIs(carId);
    }

    @Override
    public void addInspection(VehicleInspection vehicleInspection) {
        vehicleInspectionRepository.save(vehicleInspection);
    }

    @Override
    public void deleteInspectionById(Long id) {
        vehicleInspectionRepository.deleteById(id);
    }
}
