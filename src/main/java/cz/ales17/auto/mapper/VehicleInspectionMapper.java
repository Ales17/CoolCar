package cz.ales17.auto.mapper;

import cz.ales17.auto.dto.VehicleInspectionDto;
import cz.ales17.auto.entity.VehicleInspection;

public class VehicleInspectionMapper {
    public static VehicleInspection toEntity(VehicleInspectionDto dto) {
        VehicleInspection i = new VehicleInspection();
        i.setId(dto.getId());
        i.setInspectionDate(dto.getInspectionDate());
        i.setCoolantLevel(dto.getCoolantLevel());
        i.setCoolantRefilled(dto.isCoolantRefilled());
        i.setVehicle(dto.getVehicle());
        i.setPhotoUrl(dto.getPhotoUrl());
        return i;
    }

    public static VehicleInspectionDto toDto(VehicleInspection entity) {
        VehicleInspectionDto d = new VehicleInspectionDto();
        d.setId(entity.getId());
        d.setInspectionDate(entity.getInspectionDate());
        d.setCoolantLevel(entity.getCoolantLevel());
        d.setCoolantRefilled(entity.isCoolantRefilled());
        d.setPhotoUrl(entity.getPhotoUrl());
        d.setVehicle(entity.getVehicle());
        return d;
    }
}
