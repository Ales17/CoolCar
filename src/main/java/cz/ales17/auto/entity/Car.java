package cz.ales17.auto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Car extends AbstractEntity {

    private String numberPlate;
    @Column(length = 17)
    private String vinCode;

    private String label;

    private String note;
    @Column(name="production_year")
    private Short year;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity ownedBy;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<VehicleInspection> inspections;

    private String photoUrl;
}
