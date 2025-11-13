package org.example.learningmanagementsystemlab07.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {

    @NotBlank(message = "Course ID cannot be empty")
    private String courseId;

    @NotBlank(message = "Course name can not be empty")
    @Size(min = 3, max = 50, message = "Course name must be between 3 and 50 characters")
    private String courseName;

    @Positive(message = "Capacity must be greater than 0")
    @Min(value = 1)
    private int capacity;

    @NotBlank(message = "Location can not be empty")
    private String location;

    @NotNull(message = "Available to register status must not be empty")
    private Boolean availableToRegister;
}