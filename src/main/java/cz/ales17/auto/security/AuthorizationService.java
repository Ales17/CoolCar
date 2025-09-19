package cz.ales17.auto.security;

import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.VehicleInspection;
import cz.ales17.auto.exception.VehicleNotFoundException;
import cz.ales17.auto.repository.CarRepository;
import cz.ales17.auto.repository.VehicleInspectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<Car> maybeVehicle = carRepository.findById(carId);
        if(maybeVehicle.isEmpty()) return false;
        Car vehicle = maybeVehicle.get();
        long carOwnerId = vehicle.getOwnedBy().getId();
        long authenticatedUserId = SecurityUtil.getAuthenticatedUserId();
        return carOwnerId == authenticatedUserId;
    }

    public boolean isInspectionOwner(Long inspectionId) {
        Optional<VehicleInspection> maybeInspection = inspRepository.findById(inspectionId);
        if(maybeInspection.isEmpty()) return false;
        VehicleInspection insp = maybeInspection.get();
        long inspVehicle = insp.getVehicle().getId();
        return isCarOwner(inspVehicle);
    }

}
