package cz.ales17.auto.service;

import cz.ales17.auto.dto.CarDto;
import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.UserEntity;

import java.util.List;

public interface CarService {
    CarDto addCar(CarDto carDto);
    void updateCar(CarDto carDto);
    List<Car> getCarsByOwner(UserEntity user);
    List<Car> getCars();
    Car getCarById(Long id);
    void deleteCarById(Long id);
}
