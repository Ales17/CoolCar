package cz.ales17.auto.repository;

import cz.ales17.auto.entity.VehicleInspection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface VehicleInspectionRepository extends CrudRepository<VehicleInspection, Long>,PagingAndSortingRepository<VehicleInspection, Long> {
    List<VehicleInspection> findAllByVehicle_IdIs(Long vehicleId);
    Page<VehicleInspection> findAllByVehicle_IdIs(Long vehicleId, Pageable pageable);
}
