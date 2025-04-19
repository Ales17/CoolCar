package cz.ales17.auto;

import cz.ales17.auto.entity.*;
import cz.ales17.auto.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Profile("dev")
public class DatabaseSeeder {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BrandRepository brandRepository;

    private final PasswordEncoder passwordEncoder;

    private final CarRepository carRepository;

    private final VehicleInspectionRepository vehicleInspectionRepository;

    @Transactional
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Role role = new Role((byte) 1, "USER", null);
            Role savedRole = roleRepository.save(role);

            UserEntity user = new UserEntity();
            user.setUsername("user");
            user.setFirstName("Jan");
            user.setLastName("Novak");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRoles(Set.of(savedRole));
            userRepository.save(user);
            Brand brandEntity = new Brand("Skoda");
            brandRepository.save(brandEntity);


            Car car = Car.builder()
                    .brand(brandEntity)
                    .label("Moje škodovka")
                    .year((short) 2020)
                    .numberPlate("8H80000")
                    .ownedBy(user)
                    .photoUrl("https://picsum.photos/800/600")
                    .build();

            carRepository.save(car);

            LocalDate inspectionDate = LocalDate.now();
            FluidLevel level = FluidLevel.OK;
            boolean coolantRefilled = false;
            String photoUrl = "https://picsum.photos/800/600";
            VehicleInspection inspection = VehicleInspection.builder()
                    .inspectionDate(inspectionDate)
                    .coolantLevel(level)
                    .coolantRefilled(coolantRefilled)
                    .vehicle(car)
                    .photoUrl(photoUrl)
                    .build();

            vehicleInspectionRepository.save(inspection);


        };
    }
}
