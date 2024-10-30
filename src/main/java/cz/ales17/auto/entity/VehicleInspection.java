package cz.ales17.auto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class VehicleInspection extends AbstractEntity {
    private LocalDate inspectionDate;
    private FluidLevel coolantLevel;
    private boolean coolantRefilled;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Car vehicle;


}
