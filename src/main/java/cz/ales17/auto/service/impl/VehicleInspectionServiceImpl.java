package cz.ales17.auto.service.impl;

import cz.ales17.auto.dto.VehicleInspectionDto;
import cz.ales17.auto.entity.VehicleInspection;
import cz.ales17.auto.mapper.VehicleInspectionMapper;
import cz.ales17.auto.repository.VehicleInspectionRepository;
import cz.ales17.auto.service.VehicleInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleInspectionServiceImpl implements VehicleInspectionService {
    @Autowired
    private VehicleInspectionRepository inspectionRepository;

    @Override
    public List<VehicleInspection> findByCarId(Long carId) {
        return inspectionRepository.findAllByVehicle_IdIs(carId);
    }

    @Override
    public List<VehicleInspectionDto> findByVehicleId(Long vehicleId) {
        return inspectionRepository.findAllByVehicle_IdIs(vehicleId).stream().map(VehicleInspectionMapper::toDto).collect(Collectors.toList());
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

    @Override
    public Page<VehicleInspectionDto> findByVehicleIdPaginated(Long carId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VehicleInspection> inspectionPage = inspectionRepository.findAllByVehicle_IdIs(carId, pageable);
        return inspectionPage.map(VehicleInspectionMapper::toDto);
    }
}
