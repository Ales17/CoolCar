package cz.ales17.auto.service.impl;

import cz.ales17.auto.dto.VehicleInspectionDto;
import cz.ales17.auto.entity.VehicleInspection;
import cz.ales17.auto.mapper.VehicleInspectionMapper;
import cz.ales17.auto.repository.VehicleInspectionRepository;
import cz.ales17.auto.service.VehicleInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleInspectionServiceImpl implements VehicleInspectionService {
    @Autowired
    private VehicleInspectionRepository inspectionRepository;

    @Override
    public List<VehicleInspection> findByCarId(Long carId) {
        return inspectionRepository.findAllByVehicle_IdIs(carId);
    }

    @Override
    public void addInspection(VehicleInspection vehicleInspection) {
        inspectionRepository.save(vehicleInspection);
    }

    @Override
    public void deleteInspectionById(Long id) {
        inspectionRepository.deleteById(id);
    }

    @Override
    public VehicleInspectionDto findInspectionById(Long id) {
        VehicleInspection vehicleInspection = inspectionRepository.findById(id).orElseThrow(RuntimeException::new);
        return VehicleInspectionMapper.toDto(vehicleInspection);
    }
}
