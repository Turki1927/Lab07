package org.example.learningmanagementsystemlab07.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Student {

    @NotEmpty(message = "ID can not be empty")
    private String id;

    @NotEmpty(message = "Name can not be empty")
    @Size(min = 3, max = 40, message = "Name must be between 3 and 40 characters")
    private String name;
    @NotNull(message = "age can not be empty")
    @Positive(message = "Age must be greater than 0")
    private int age;

    @NotEmpty(message = "Major can not be empty")
    private String major;

    @Email(message = "Email must be valid")
    private String email;

    @DecimalMin(value = "0.0", message = "GPA can not be negative")
    @DecimalMax(value = "4.0", message = "GPA can not exceed 4.0")
    private double gpa;

    @NotNull(message = "Graduation date can not be null")
    private LocalDate graduationDate;
}
