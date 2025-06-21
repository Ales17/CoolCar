package cz.ales17.auto.controller;

import cz.ales17.auto.dto.CarDto;
import cz.ales17.auto.dto.VehicleInspectionDto;
 import cz.ales17.auto.entity.FluidLevel;
import cz.ales17.auto.entity.VehicleInspection;
import cz.ales17.auto.mapper.CarMapper;
import cz.ales17.auto.service.CarService;
import cz.ales17.auto.service.StorageService;
import cz.ales17.auto.service.VehicleInspectionService;
import cz.ales17.auto.storage.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/cars/{carId}/inspections")
@Controller
@RequiredArgsConstructor
public class InspectionController {

    private final StorageService storageService;

    private final CarService carService;

    private final VehicleInspectionService vehicleInspectionService;

    @PreAuthorize("@authorizationService.isCarOwner(#carId)")
    @GetMapping("/new")
    public String newInspection(@PathVariable Long carId, Model m) {
        CarDto car = carService.getCarById(carId);
        m.addAttribute("car", car);
        VehicleInspection inspection = new VehicleInspection();
        inspection.setInspectionDate(LocalDate.now());
        m.addAttribute("inspection", inspection);
        List<FluidLevel> fluidLevels = List.of(FluidLevel.OK, FluidLevel.LOW, FluidLevel.EMPTY, FluidLevel.OVERFILLED);
        m.addAttribute("fluidLevels", fluidLevels);
        m.addAttribute("title", String.format("Nová prohlídka (%s)", car.getNumberPlate()));
        return "inspections-create";
    }

    @PreAuthorize("@authorizationService.isCarOwner(#carId)")
    @PostMapping(value = "/new", consumes = "multipart/form-data")
    public String createNewInspection(@PathVariable Long carId, Model m, @ModelAttribute("inspection") VehicleInspection inspection, @RequestParam("photo") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                String uploadedFilename = storageService.store(file);
                inspection.setPhotoUrl(String.format("%s%s", FileUtil.ROOT_LOCATION, uploadedFilename));
            } catch (Exception e) {
                e.printStackTrace();
                CarDto car = carService.getCarById(carId);
                m.addAttribute("car", car);
                m.addAttribute("message", "Chyba při nahrání souboru");
                m.addAttribute("inspection", inspection);
                List<FluidLevel> fluidLevels = List.of(FluidLevel.class.getEnumConstants());
                m.addAttribute("fluidLevels", fluidLevels);
                return "inspections-create";
            }
        }

        CarDto car = carService.getCarById(carId);
        inspection.setVehicle(CarMapper.toEntity(car));
        vehicleInspectionService.addInspection(inspection);
        return "redirect:/cars/" + carId;
    }

    @PreAuthorize("@authorizationService.isCarOwner(#carId)")
    @DeleteMapping("/{inspectionId}")
    public ResponseEntity<String> deleteInspection(@PathVariable Long carId, @PathVariable("inspectionId") Long inspectionId) {
        vehicleInspectionService.deleteInspectionById(inspectionId);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PreAuthorize("@authorizationService.isCarOwner(#carId)")
    @GetMapping("/{inspectionId}")
    public String showInspection(@PathVariable Long carId, @PathVariable Long inspectionId, Model m) {
        VehicleInspectionDto dto = vehicleInspectionService.findInspectionById(inspectionId);
        m.addAttribute("title", "Detail prohlídky");
        m.addAttribute("inspection", dto);
        return "inspections-detail";
    }


}

