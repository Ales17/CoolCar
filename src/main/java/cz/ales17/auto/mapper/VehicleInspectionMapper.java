package cz.ales17.auto.mapper;

import cz.ales17.auto.dto.VehicleInspectionDto;
import cz.ales17.auto.entity.VehicleInspection;

public class VehicleInspectionMapper {
    public VehicleInspection toEntity(VehicleInspectionDto dto) {
        return VehicleInspection.builder()
                .inspectionDate(dto.getInspectionDate())
                .coolantLevel(dto.getCoolantLevel())
                .coolantRefilled(dto.isCoolantRefilled())
                .vehicle(dto.getVehicle())
                .build();
    }

    public VehicleInspectionDto toDto(VehicleInspection entity) {
        return VehicleInspectionDto.builder()
                .inspectionDate(entity.getInspectionDate())
                .coolantLevel(entity.getCoolantLevel())
                .coolantRefilled(entity.isCoolantRefilled())
                .vehicle(entity.getVehicle())
                .build();
    }
}
