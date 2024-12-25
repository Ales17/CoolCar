package cz.ales17.auto.dto;

import cz.ales17.auto.entity.Brand;
import cz.ales17.auto.entity.UserEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private Long id;
    @NotEmpty
    private String numberPlate;
    @Length(min = 17, max = 17)
    private String vinCode;
    @NotEmpty
    private String label;

    private String note;

    private UserEntity ownedBy;

    private Brand brand;

    @NotNull
    @Min(1900)
    @Max(2050)
    private Short year;

    private List<VehicleInspectionDto> inspections;

    private String photoUrl;
}
