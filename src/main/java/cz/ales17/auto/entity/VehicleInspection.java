package cz.ales17.auto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_inspection")
public class VehicleInspection extends AbstractEntity {
    private LocalDate inspectionDate;
    private FluidLevel coolantLevel;
    private boolean coolantRefilled;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Car vehicle;

    private String photoUrl;
}
