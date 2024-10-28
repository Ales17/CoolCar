package cz.ales17.auto.repository;

import cz.ales17.auto.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
