package cz.ales17.auto.controller;

import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.UserEntity;
import cz.ales17.auto.repository.UserRepository;
import cz.ales17.auto.service.CarService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RolesAllowed("ADMIN")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CarService carService;
    private final UserRepository userRepository;

    @GetMapping("/cars")
    public String listCars(Model m) {
        List<Car> cars = carService.getCars();
        m.addAttribute("cars", cars);
        return "cars-list";
    }

    @GetMapping("/users")
    public String listUsers(Model m) {
        List<UserEntity> users = userRepository.findAll();
        m.addAttribute("users", users);
        return "users-list";
    }
}
