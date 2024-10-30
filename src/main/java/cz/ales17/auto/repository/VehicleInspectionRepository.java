package cz.ales17.auto.repository;

import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.VehicleInspection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleInspectionRepository extends JpaRepository<VehicleInspection, Long> {
    List<VehicleInspection> findAllByVehicle_IdIs(Long vehicleId);
}
