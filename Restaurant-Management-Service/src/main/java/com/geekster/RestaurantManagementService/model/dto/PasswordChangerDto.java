package com.geekster.RestaurantManagementService.model.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangerDto {
    @Email
    String email;
    String oldPassword;
    String newPassword;
}
