package cz.ales17.auto.service.impl;

import cz.ales17.auto.dto.CarDto;
import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.UserEntity;
import cz.ales17.auto.mapper.VehicleMapper;
import cz.ales17.auto.repository.CarRepository;
import cz.ales17.auto.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.ales17.auto.mapper.VehicleMapper.toDto;
import static cz.ales17.auto.mapper.VehicleMapper.toEntity;
import static cz.ales17.auto.security.SecurityUtil.getPrincipal;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public CarDto addCar(CarDto carDto) {
        Car car = VehicleMapper.toEntity(carDto);
        car.setOwnedBy(getPrincipal());
        Car newCar = carRepository.save(car);
        return toDto(newCar);
    }

    @Override
    public void updateCar(CarDto carDto) {
        Car vehicle = carRepository.findById(carDto.getId()).orElse(null);
        if (vehicle == null) {
            throw new IllegalArgumentException("Car with ID " + carDto.getId() + " does not exist.");
        }
        vehicle.setNumberPlate(carDto.getNumberPlate());
        vehicle.setVinCode(carDto.getVinCode());
        vehicle.setLabel(carDto.getLabel());
        vehicle.setNote(carDto.getNote());
        vehicle.setYear(carDto.getYear());
        vehicle.setBrand(carDto.getBrand());
        carRepository.save(vehicle);
    }

    @Override
    public List<CarDto> getCarsByOwner(UserEntity user) {
        return carRepository.findAllByOwnedByIs(user).stream().map((e)-> toDto(e)).toList();
    }

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public List<CarDto> getCarsByCurrentUser() {
        return getCarsByOwner(getPrincipal());
    }

    @Override
    public CarDto getCarById(Long id) {
        Car vehicle = carRepository.findById(id).get();
        return toDto(vehicle);
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }


}
