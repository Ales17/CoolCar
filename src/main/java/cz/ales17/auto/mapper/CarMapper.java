package cz.ales17.auto.mapper;

import cz.ales17.auto.dto.CarDto;
import cz.ales17.auto.entity.Car;

public class CarMapper {
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
        return Car.builder()
                .numberPlate(dto.getNumberPlate())
                .vinCode(dto.getVinCode())
                .label(dto.getLabel())
                .note(dto.getNote())
                .year(dto.getYear())
                .brand(dto.getBrand())
                .ownedBy(dto.getOwnedBy())
                .photoUrl(dto.getPhotoUrl())
                .build();
    }
}
