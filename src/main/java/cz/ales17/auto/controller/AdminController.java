package cz.ales17.auto.controller;

import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.Role;
import cz.ales17.auto.entity.UserEntity;
import cz.ales17.auto.repository.RoleRepository;
import cz.ales17.auto.repository.UserRepository;
import cz.ales17.auto.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CarService carService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/car")
    public String listCars(Model m) {
        List<Car> cars = carService.getCars();
        m.addAttribute("cars", cars);
        return "admin";
    }


    @GetMapping("/users")
    public ResponseEntity<String> listUsers(Model m) {
        UserEntity ales = new UserEntity();
        ales.setUsername("ales");
        ales.setPassword(passwordEncoder.encode("password"));
        Role admin = roleRepository.findRoleByName("ADMIN");
        Role user = roleRepository.findRoleByName("USER");
        ales.setRoles(Set.of(user, admin));
        userRepository.save(ales);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
