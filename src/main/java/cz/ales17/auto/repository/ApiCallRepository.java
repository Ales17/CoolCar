package cz.ales17.auto.repository;

import cz.ales17.auto.entity.ApiCall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ApiCallRepository extends JpaRepository<ApiCall, Long> {
    Optional<ApiCall> findApiCallByVehicle_Id(Long vehicleId);

    Optional<ApiCall> findTopByVehicle_VinCodeAndCreatedOnAfterOrderByCreatedOnDesc(String vin, LocalDateTime after);
}
