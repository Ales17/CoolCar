package cz.ales17.auto.controller;

import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.FluidLevel;
import cz.ales17.auto.entity.VehicleInspection;
import cz.ales17.auto.service.CarService;
import cz.ales17.auto.service.StorageService;
import cz.ales17.auto.service.VehicleInspectionService;
import cz.ales17.auto.storage.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/cars/{carId}/inspections")
@Controller
public class InspectionController {

    @Autowired
    private StorageService storageService;
    @Autowired
    private CarService carService;

    @Autowired
    private VehicleInspectionService vehicleInspectionService;

    @GetMapping("/new")
    public String newInspection(@PathVariable Long carId, Model m) {
        Car car = carService.getCarById(carId);
        m.addAttribute("car", car);
        VehicleInspection inspection = new VehicleInspection();
        inspection.setInspectionDate(LocalDate.now());
        m.addAttribute("inspection", inspection);
        List<FluidLevel> fluidLevels = List.of(FluidLevel.class.getEnumConstants());
        m.addAttribute("fluidLevels", fluidLevels);
        return "inspections-create";
    }


    @PostMapping(value="/new", consumes = "multipart/form-data")
    public String createNewInspection(@PathVariable Long carId, Model m, @ModelAttribute("inspection") VehicleInspection inspection, @RequestParam("photo") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                String uploadedFilename = storageService.store(file);
                inspection.setPhotoUrl(String.format("%s%s", FileUtil.ROOT_LOCATION, uploadedFilename));
            } catch (Exception e) {
                e.printStackTrace();
                Car car = carService.getCarById(carId);
                m.addAttribute("car", car);
                m.addAttribute("message", "Chyba při nahrání souboru");
                m.addAttribute("inspection", inspection);
                List<FluidLevel> fluidLevels = List.of(FluidLevel.class.getEnumConstants());
                m.addAttribute("fluidLevels", fluidLevels);
                return "inspections-create";
            }
        }

        Car car = carService.getCarById(carId);
        inspection.setVehicle(car);
        vehicleInspectionService.addInspection(inspection);
        return "redirect:/cars/"+carId;
    }

    @DeleteMapping("/{inspectionId}")
    public ResponseEntity<String> deleteInspection(@PathVariable("inspectionId") Long inspectionId) {
        vehicleInspectionService.deleteInspectionById(inspectionId);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}

