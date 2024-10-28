package cz.ales17.auto.controller;

import cz.ales17.auto.entity.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {
    @GetMapping({"/hello", "/"})
    public String testMethod() {
        return "hello";
    }

    @GetMapping("/car/new")
    public String newCarForm(Model m) {
        Car car = new Car();
        m.addAttribute("car", car);
        return "cars-create";
    }

}
