package cz.ales17.auto.controller;

import cz.ales17.auto.dto.CarDto;
import cz.ales17.auto.entity.Brand;
import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.UserEntity;
import cz.ales17.auto.entity.VehicleInspection;
import cz.ales17.auto.repository.UserRepository;
import cz.ales17.auto.service.BrandService;
import cz.ales17.auto.service.CarService;
import cz.ales17.auto.service.StorageService;
import cz.ales17.auto.storage.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarController {

    private final BrandService brandService;
    private final StorageService storageService;
    private final CarService carService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String homepage() {
        return "home";
    }

    @GetMapping("/cars")
    public String listCars(Model m) {
        List<Car> cars = carService.getCars();
        m.addAttribute("cars", cars);
        return "cars-list";
    }

    @GetMapping("/cars/{carId}")
    public String carDetail(@PathVariable("carId") Long carId, Model m) {
        Car car = carService.getCarById(carId);
        m.addAttribute("car", car);
        List<VehicleInspection> inspections = car.getInspections();
        m.addAttribute("inspections", inspections);
        return "cars-detail";
    }



    @GetMapping("/cars/new")
    public String newCarForm(Model m) {
        CarDto car = new CarDto();
        m.addAttribute("car", car);
        List<Brand> brands = brandService.getBrands();
        m.addAttribute("brands", brands);
        return "cars-create";
    }

    @PostMapping(value="/cars/new", consumes = "multipart/form-data")
    public String newCar(@ModelAttribute("car") CarDto car, @RequestParam("brand-id") Long brandId) {
        UserEntity ales = userRepository.findById(1L).get();
        car.setOwnedBy(ales);
        Brand brand = brandService.getBrand(brandId);
        car.setBrand(brand);
        carService.addCar(car);
        return "redirect:/cars";
    }

    @DeleteMapping("/cars/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable("carId") Long carId) {
        carService.deleteCarById(carId);
        return new ResponseEntity<>(carId.toString(), HttpStatus.OK);
    }
}
