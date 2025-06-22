package cz.ales17.auto.mapper;

import cz.ales17.auto.dto.CarDto;
import cz.ales17.auto.entity.Car;

public class VehicleMapper {
    public static CarDto toDto(Car entity) {
        return CarDto.builder()
                .id(entity.getId())
                .numberPlate(entity.getNumberPlate())
                .vinCode(entity.getVinCode())
                .label(entity.getLabel())
                .note(entity.getNote())
                .year(entity.getYear())
                .brand(entity.getBrand())
                .ownedBy(entity.getOwnedBy())
                .photoUrl(entity.getPhotoUrl())
                .build();
    }

    public static Car toEntity(CarDto dto) {
        Car vehicle = new Car();
        vehicle.setId(dto.getId());
        vehicle.setNumberPlate(dto.getNumberPlate());
        vehicle.setVinCode(dto.getVinCode());
        vehicle.setLabel(dto.getLabel());
        vehicle.setNote(dto.getNote());
        vehicle.setYear(dto.getYear());
        vehicle.setBrand(dto.getBrand());
        vehicle.setOwnedBy(dto.getOwnedBy());
        vehicle.setPhotoUrl(dto.getPhotoUrl());
        return vehicle;
    }
}
