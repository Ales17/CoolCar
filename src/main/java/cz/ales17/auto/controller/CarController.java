package cz.ales17.auto.controller;

import cz.ales17.auto.dto.CarDto;
import cz.ales17.auto.dto.VehicleInspectionDto;
import cz.ales17.auto.entity.Brand;
import cz.ales17.auto.entity.Car;
import cz.ales17.auto.service.BrandService;
import cz.ales17.auto.service.CarService;
import cz.ales17.auto.service.StorageService;
import cz.ales17.auto.service.VehicleInspectionService;
import cz.ales17.auto.storage.FileUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final VehicleInspectionService inspectionService;
    @GetMapping("/")
    public String homepage() {
        return "redirect:/cars";
    }

    @GetMapping("/cars")
    public String listCars(Model m) {
        List<Car> cars = carService.getCarsByCurrentUser();
        m.addAttribute("cars", cars);
        m.addAttribute("title", "Moje auta");
        return "cars-list";
    }

    @PreAuthorize("@authorizationService.isCarOwner(#carId)")
    @GetMapping("/cars/{carId}")
    public String carDetail(@PathVariable("carId") Long carId,
                            @RequestParam(defaultValue = "1", name = "page") int requestedPage,
                            @RequestParam(defaultValue = "12",name = "size") int requestedSize,
                            Model m) {
        Car car = carService.getCarById(carId);
        m.addAttribute("car", car);
        Page<VehicleInspectionDto> inspectionPage = inspectionService
                .findByVehicleIdPaginated(carId, requestedPage-1, requestedSize);
        m.addAttribute("inspectionPage", inspectionPage);
        m.addAttribute("currentPage", requestedPage);
        m.addAttribute("totalPages", inspectionPage.getTotalPages());
        m.addAttribute("carId", carId);
        m.addAttribute("title", car.getLabel());
        return "cars-detail";
    }


    @GetMapping("/cars/new")
    public String newCarForm(Model m) {
        m.addAttribute("car", new CarDto());
        List<Brand> brands = brandService.getBrands();
        m.addAttribute("brands", brands);
        m.addAttribute("title", "Nové auto");
        return "cars-create";
    }

    @PreAuthorize("@authorizationService.isCarOwner(#carId)")
    @GetMapping("/cars/{carId}/edit")
    public String editCarForm(@PathVariable("carId") Long carId, Model m) {
        Car car = carService.getCarById(carId);
        m.addAttribute("car", car);
        List<Brand> brands = brandService.getBrands();
        m.addAttribute("brands", brands);
        m.addAttribute("title", "Upravit auto");
        return "cars-create";
    }

    @PreAuthorize("@authorizationService.canSaveCar(#car.getId())")
    @PostMapping(value = "/cars", consumes = "multipart/form-data")
    public String saveCar(@RequestParam("brand-id") Optional<Long> brandId, @RequestParam("photo") MultipartFile file, @Valid @ModelAttribute("car") CarDto car, BindingResult result, Model m) {

        if (result.hasErrors() || brandId.isEmpty()) {
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
    @PreAuthorize("@authorizationService.isCarOwner(#carId)")
    @DeleteMapping("/cars/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable("carId") Long carId) {
        carService.deleteCarById(carId);
        return new ResponseEntity<>(carId.toString(), HttpStatus.OK);
    }
}
