package cz.ales17.auto.entity;

import lombok.Getter;

@Getter
public enum FluidLevel {
    EMPTY("Prázdná"),
    LOW("Nízká"),
    OK("V pořádku"),
    OVERFILLED("Přeplněná");

    private final String displayName;

    FluidLevel(String displayName) {
        this.displayName = displayName;
    }
}
