package cz.ales17.auto;

import cz.ales17.auto.entity.Brand;
import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.UserEntity;
import cz.ales17.auto.repository.BrandRepository;
import cz.ales17.auto.repository.CarRepository;
import cz.ales17.auto.repository.RoleRepository;
import cz.ales17.auto.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import cz.ales17.auto.entity.Role;

@Configuration
@Profile("dev")
public class DatabaseSeeder {

    @Bean
    CommandLineRunner seedData(UserRepository userRepo, RoleRepository roleRepo, CarRepository carRepo, BrandRepository brandRepo) {
        return args -> {
            Role adminRole = roleRepo.findRoleByName("ADMIN");
            if (adminRole == null) {
                adminRole = roleRepo.save(new Role((byte) 1, "ADMIN"));
            }

            UserEntity user = new UserEntity();
            user.setUsername("admin");
            user.setPassword("$2a$12$R3nK4o.XaJBrKYFIAC2nWefb2Ti6ZF4/paTWnl4nGBJLeC2uW4Fpu"); // pass
            user.getRoles().add(adminRole);

            user = userRepo.save(user);

            Brand skoda = new Brand("Skoda");
            brandRepo.save(skoda);

            Car car = new Car();
            car.setNumberPlate("1H10000");
            car.setOwnedBy(user);
            car.setBrand(skoda);
            car.setVinCode("TMBJBXXXXXX");
            car.setYear((short) 2020);

            carRepo.save(car);

        };
    }
}
