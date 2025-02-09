package cz.ales17.auto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users = new HashSet<>();
}
