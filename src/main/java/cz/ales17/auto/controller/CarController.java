package cz.ales17.auto.controller;

import cz.ales17.auto.dto.CarDto;
import cz.ales17.auto.entity.Brand;
import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.VehicleInspection;
import cz.ales17.auto.repository.UserRepository;
import cz.ales17.auto.service.BrandService;
import cz.ales17.auto.service.CarService;
import cz.ales17.auto.service.StorageService;
import cz.ales17.auto.storage.FileUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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
        m.addAttribute("car", new CarDto());
        List<Brand> brands = brandService.getBrands();
        m.addAttribute("brands", brands);
        return "cars-create";
    }

    @GetMapping("/cars/{carId}/edit")
    public String editCarForm(@PathVariable("carId") Long carId, Model m) {
        Car car = carService.getCarById(carId);
        m.addAttribute("car", car);
        List<Brand> brands = brandService.getBrands();
        m.addAttribute("brands", brands);
        return "cars-create";
    }


    @PostMapping(value = "/cars", consumes = "multipart/form-data")
    public String saveCar(@RequestParam("brand-id") Optional<Long> brandId, @RequestParam("photo") MultipartFile file, @Valid @ModelAttribute("car") CarDto car, BindingResult result, Model m) {

        if (result.hasErrors() || brandId.isEmpty())  {
            m.addAttribute("car", car);
            List<Brand> brands = brandService.getBrands();
            m.addAttribute("brands", brands);
            return "cars-create";
        }

        if (!file.isEmpty()) {
            try {
                String uploadedFilename = storageService.store(file);
                String photoUrl = String.format("%s%s", FileUtil.ROOT_LOCATION, uploadedFilename);
                car.setPhotoUrl(photoUrl);
            } catch (Exception e) {
                e.printStackTrace();
                m.addAttribute("car", car);
                m.addAttribute("message", "Chyba při nahrání souboru");
                return "cars-create";
            }
        }

        Brand brand = brandService.getBrand(brandId.get());
        car.setBrand(brand);

        if (car.getId() == null) {
            CarDto newCar = carService.addCar(car);
            return "redirect:/cars/" + newCar.getId();
        } else {
            carService.updateCar(car);
            return "redirect:/cars/" + car.getId();
        }
    }

    @DeleteMapping("/cars/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable("carId") Long carId) {
        carService.deleteCarById(carId);
        return new ResponseEntity<>(carId.toString(), HttpStatus.OK);
    }
}
