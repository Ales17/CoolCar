package cz.ales17.auto.dto;

import cz.ales17.auto.entity.Brand;
import cz.ales17.auto.entity.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private Long id;
    @NotEmpty
    private String numberPlate;
    @NotEmpty
    private String vinCode;
    @NotEmpty
    private String label;

    private String note;

    private UserEntity ownedBy;

    private Brand brand;

    @NotNull
    private short year;

    private List<VehicleInspectionDto> inspections;

}
