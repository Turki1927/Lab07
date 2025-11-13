package org.example.learningmanagementsystemlab07.Controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.learningmanagementsystemlab07.Api.ApiResponse;
import org.example.learningmanagementsystemlab07.Model.Assignment;
import org.example.learningmanagementsystemlab07.Service.AssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/assignment")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;

    @GetMapping("/get")
    public ResponseEntity getAssignments() {
        return ResponseEntity.status(200).body(assignmentService.getAssignments());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAssignment(@Valid @RequestBody Assignment assignment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isAdded = assignmentService.addAssignment(assignment);
        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment added successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Assignment ID already exists"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateAssignment(@PathVariable String id, @Valid @RequestBody Assignment assignment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = assignmentService.updateAssignment(id, assignment);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Assignment not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAssignment(@PathVariable String id) {
        boolean isDeleted = assignmentService.deleteAssignment(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Assignment not found"));
        }
    }

    @GetMapping("/get-submitted")
    public ResponseEntity getSubmittedAssignments() {
        if(assignmentService.getSubmitted().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("there is no submitted assignment"));
        }

        return ResponseEntity.status(200).body(assignmentService.getSubmitted());
    }

    @PutMapping("/submit/{id}")
    public ResponseEntity submitAssignment(@PathVariable String id) {
        boolean isSubmitted = assignmentService.submitAssignment(id);
        if (isSubmitted) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment submitted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Assignment not found"));
        }
    }

    @GetMapping("/check/{id}")
    public ResponseEntity checkAssignment(@PathVariable String id) {
        Assignment a = assignmentService.checkAssignment(id);
        if (a == null) {
            return ResponseEntity.status(400).body(new ApiResponse("Assignment not found"));
        } else {
            return ResponseEntity.status(200).body(a);
        }
    }

    @PutMapping("/extend/{id}")
    public ResponseEntity extendDeadline(@PathVariable String id) {
        boolean isExtended = assignmentService.extendDeadline(id);
        if (isExtended) {
            return ResponseEntity.status(200).body(new ApiResponse("Deadline extended by one day"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Assignment not found"));
        }
    }

}