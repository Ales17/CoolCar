package cz.ales17.auto.dto;

import cz.ales17.auto.entity.Car;
import cz.ales17.auto.entity.FluidLevel;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
@Builder
@Data
public class VehicleInspectionDto {
    @NotNull
    private Long id;
    @NotNull
    private LocalDate inspectionDate;
    @NotNull
    private FluidLevel coolantLevel;
    @NotNull
    private boolean coolantRefilled;
    @NotNull
    private Car vehicle;
    private String photoUrl;
}
