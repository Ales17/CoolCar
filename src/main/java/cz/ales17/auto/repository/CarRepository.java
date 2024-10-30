package cz.ales17.auto.repository;

import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByOwnedByIs(UserEntity user);
}
