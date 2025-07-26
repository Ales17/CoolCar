package cz.ales17.auto.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends AbstractEntity {
    public String name;

    @Override
    public String toString() {
        return name;
    }
}
