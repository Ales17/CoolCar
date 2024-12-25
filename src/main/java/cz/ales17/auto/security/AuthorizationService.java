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
        String ownerUsername = car.getOwnedBy().getUsername();
        String sessionUsername = SecurityUtil.getSessionUsername();
        return ownerUsername.equals(sessionUsername);

    }


}
