package cz.ales17.auto.security;

import cz.ales17.auto.entity.Car;
import cz.ales17.auto.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final CarRepository carRepository;

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


}
