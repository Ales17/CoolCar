package cz.ales17.auto.mapper;

import cz.ales17.auto.dto.VehicleInspectionDto;
import cz.ales17.auto.entity.VehicleInspection;

public class VehicleInspectionMapper {
    public static VehicleInspection toEntity(VehicleInspectionDto dto) {
        return VehicleInspection.builder()
                .inspectionDate(dto.getInspectionDate())
                .coolantLevel(dto.getCoolantLevel())
                .coolantRefilled(dto.isCoolantRefilled())
                .vehicle(dto.getVehicle())
                .photoUrl(dto.getPhotoUrl())
                .build();
    }

    public static VehicleInspectionDto toDto(VehicleInspection entity) {
        return VehicleInspectionDto.builder()
                .id(entity.getId())
                .inspectionDate(entity.getInspectionDate())
                .coolantLevel(entity.getCoolantLevel())
                .coolantRefilled(entity.isCoolantRefilled())
                .vehicle(entity.getVehicle())
                .photoUrl(entity.getPhotoUrl())
                .build();
    }
}
