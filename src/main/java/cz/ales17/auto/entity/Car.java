package cz.ales17.auto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car extends AbstractEntity {

    private String numberPlate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity ownedBy;

}
