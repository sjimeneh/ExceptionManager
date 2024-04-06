package com.example.ExceptionManager.controller.DTO;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@Valid
public class UserDTO {
    @NotBlank(message = "The [name] is a required field")
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$",message = "The [name] field can only contain letters, numbers or spaces")
    private String name;

    @NotNull(message = "The [dateOfBirth] is a required field")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @NotBlank
    @Email(regexp = "^[\\p{L}\\p{N}\\.\\-_+]+@[\\p{L}\\p{N}\\-]+(\\.[\\p{L}\\p{N}-]+)+$",message = "The [email] field not in a format valid")
    private String email;
}
