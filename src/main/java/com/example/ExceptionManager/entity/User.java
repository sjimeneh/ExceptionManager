package com.example.ExceptionManager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The [name] is a required field")
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s]+$",message = "The [name] field can only contain letters, numbers or spaces")
    private String name;

    @NotNull(message = "The [dateOfBirth] is a required field")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @NotBlank
    @Pattern(regexp = "^[\\p{L}\\p{N}\\.\\-_+]+@[\\p{L}\\p{N}\\-]+(\\.[\\p{L}\\p{N}-]+)*$",message = "The [email] field not in a format valid")
    private String email;

}
