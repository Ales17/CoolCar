package cz.ales17.auto.service.impl;

import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.UserEntity;
import cz.ales17.auto.repository.CarRepository;
import cz.ales17.auto.repository.UserRepository;
import cz.ales17.auto.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public void addCar(Car car) {
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
    public Car getCarById(Long id) {
        return carRepository.findById(id).get();
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }


}
