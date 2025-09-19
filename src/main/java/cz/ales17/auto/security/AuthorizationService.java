package cz.ales17.auto.security;

import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.VehicleInspection;
import cz.ales17.auto.repository.CarRepository;
import cz.ales17.auto.repository.VehicleInspectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final CarRepository carRepository;
    private final VehicleInspectionRepository inspRepository;
    public boolean canSaveCar(Long carId) {
        if (carId == null) {
            return true;
        }
        return isCarOwner(carId);
    }

    public boolean isCarOwner(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(RuntimeException::new);
        long carOwnerId = car.getOwnedBy().getId();
        long authenticatedUserId = SecurityUtil.getAuthenticatedUserId();
        return carOwnerId == authenticatedUserId;
    }

    public boolean isInspectionOwner(Long inspectionId) {
        VehicleInspection inspection = inspRepository.findById(inspectionId).orElseThrow(RuntimeException::new);
        long inspVehicle = inspection.getVehicle().getId();
        return isCarOwner(inspVehicle);
    }

}
