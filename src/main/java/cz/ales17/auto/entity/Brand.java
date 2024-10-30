package cz.ales17.auto.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Brand extends AbstractEntity {
    public String name;
}
