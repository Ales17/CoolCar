package cz.ales17.auto.controller;

import cz.ales17.auto.dto.CarDto;
import cz.ales17.auto.dto.VehicleInspectionDto;
import cz.ales17.auto.entity.FluidLevel;
import cz.ales17.auto.entity.VehicleInspection;
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

@Controller
@RequiredArgsConstructor
public class InspectionController {

    private final StorageService storageService;

    private final CarService carService;

    private final VehicleInspectionService inspService;

    private final List<FluidLevel> fluidLevels = List.of(FluidLevel.OK, FluidLevel.LOW, FluidLevel.EMPTY, FluidLevel.OVERFILLED);


    @PreAuthorize("@authorizationService.isCarOwner(#vehicleId)")
    @GetMapping("/inspections/new")
    public String newInspection(Model m,
                                @RequestParam(value = "vehicleId") Long vehicleId) {
        CarDto car = carService.getCarById(vehicleId);
        m.addAttribute("car", car);
        VehicleInspection inspection = new VehicleInspection();
        inspection.setInspectionDate(LocalDate.now());
        m.addAttribute("inspection", inspection);
        m.addAttribute("fluidLevels", fluidLevels);
        m.addAttribute("title", String.format("Nová prohlídka (%s)", car.getNumberPlate()));
        return "inspections-create";
    }

    @PreAuthorize("@authorizationService.isCarOwner(#carId)")
    @PostMapping(value = "/inspections/save", consumes = "multipart/form-data")
    public String saveInspection(@RequestParam Long vehicleId,
                                 Model m,
                                 @ModelAttribute("inspection") VehicleInspectionDto inspection,
                                 @RequestParam("photo") MultipartFile file) {
        if (!file.isEmpty() && !file.getName().isEmpty()) {
                try {
                    String uploadedFilename = storageService.store(file);
                    inspection.setPhotoUrl(String.format("%s%s", FileUtil.ROOT_LOCATION, uploadedFilename));
                } catch (Exception e) {
                    CarDto vehicle = carService.getCarById(vehicleId);
                    m.addAttribute("car", vehicle);
                    m.addAttribute("message", "Chyba při nahrání souboru: " + e.getMessage());
                    m.addAttribute("inspection", inspection);
                    m.addAttribute("fluidLevels", fluidLevels);
                    return "inspections-create";
                }
            }
        inspService.saveInspection(inspection, vehicleId);
        return "redirect:/cars/" + vehicleId;
    }

    @PreAuthorize("@authorizationService.isInspectionOwner(#inspectionId)")
    @GetMapping({"/inspections/{inspectionId}/edit"})
    public String editInspectionPage(@PathVariable Long inspectionId, Model m) {
        VehicleInspectionDto existingInspection = inspService.findInspectionById(inspectionId);
        Long vehicleId = existingInspection.getVehicle().getId();
        m.addAttribute("vehicleId", vehicleId);
        m.addAttribute("fluidLevels", fluidLevels);
        m.addAttribute("inspection", existingInspection);
        return "inspections-create";
    }

    @PreAuthorize("@authorizationService.isInspectionOwner(#inspId)")
    @DeleteMapping("/inspections/{inspectionId}")
    public ResponseEntity<String> deleteInspection(@PathVariable("inspectionId") Long inspId) {
        inspService.deleteInspectionById(inspId);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PreAuthorize("@authorizationService.isInspectionOwner(#inspectionId)")
    @GetMapping("/inspections/{inspectionId}")
    public String showInspection(@PathVariable Long inspectionId, Model m) {
        VehicleInspectionDto dto = inspService.findInspectionById(inspectionId);
        m.addAttribute("title", "Detail prohlídky");
        m.addAttribute("inspection", dto);
        return "inspections-detail";
    }


}

