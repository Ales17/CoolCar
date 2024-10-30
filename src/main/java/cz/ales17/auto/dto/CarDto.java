package cz.ales17.auto.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CarDto {
    private Long id;
    @NotEmpty
    private String numberPlate;
    @NotEmpty
    private String vinCode;
    @NotEmpty
    private String label;

    private String note;

    @NotNull
    private short year;

    private List<VehicleInspectionDto> inspections;

}
