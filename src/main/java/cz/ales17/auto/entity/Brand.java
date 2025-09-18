package cz.ales17.auto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brand")
public class Brand extends AbstractEntity {
    public String name;

    @Override
    public String toString() {
        return name;
    }
}
