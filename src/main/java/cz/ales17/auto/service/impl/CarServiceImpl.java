package cz.ales17.auto.service.impl;

import cz.ales17.auto.dto.CarDto;
import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.UserEntity;
import cz.ales17.auto.mapper.CarMapper;
import cz.ales17.auto.repository.CarRepository;
import cz.ales17.auto.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.ales17.auto.mapper.CarMapper.toDto;
import static cz.ales17.auto.security.SecurityUtil.getPrincipal;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public CarDto addCar(CarDto carDto) {
        Car car = CarMapper.toEntity(carDto);
        car.setOwnedBy(getPrincipal());
        Car newCar = carRepository.save(car);
        return toDto(newCar);
    }

    @Override
    public void updateCar(CarDto carDto) {
        Car car = getCarById(carDto.getId());
        car.setNumberPlate(carDto.getNumberPlate());
        car.setVinCode(carDto.getVinCode());
        car.setLabel(carDto.getLabel());
        car.setNote(carDto.getNote());
        car.setYear(carDto.getYear());
        car.setBrand(carDto.getBrand());
        carRepository.save(car);
    }

    @Override
    public List<Car> getCarsByOwner(UserEntity user) {
        return carRepository.findAllByOwnedByIs(user);
    }

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getCarsByCurrentUser() {
        return getCarsByOwner(getPrincipal());
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).get();
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }


}
