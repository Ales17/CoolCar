package cz.ales17.auto.dto;

import lombok.Data;

@Data
public class PasswordChangeDto {
    private String oldPassword;

    private String newPassword;

    private String newPasswordAgain;
}
