package org.example.learningmanagementsystemlab07.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class Assignment {

    @NotEmpty(message = "Assignment ID can not be empty")
    private String assignmentId;

    @NotEmpty(message = "Title can not be empty")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;

    @NotEmpty(message = "Description can not be empty")
    @Size(min = 10, message = "Description must be at least 10 characters long")
    private String description;

    @NotNull(message = "isSubmitted status must be provided")
    private Boolean isSubmitted;
    @NotNull(message = "Deadline cannot be null")
    @Future(message = "Deadline must be a future date")
    private LocalDate deadline;
}
