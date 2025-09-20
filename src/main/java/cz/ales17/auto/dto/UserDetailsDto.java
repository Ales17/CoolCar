package cz.ales17.auto.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserDetailsDto {
    public String firstName;
    public String lastName;
    @Email
    public String email;
}
