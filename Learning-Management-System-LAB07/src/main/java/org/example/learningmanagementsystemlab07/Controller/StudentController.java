package org.example.learningmanagementsystemlab07.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.learningmanagementsystemlab07.Api.ApiResponse;
import org.example.learningmanagementsystemlab07.Model.Student;
import org.example.learningmanagementsystemlab07.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getStudents() {
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isAdded = studentService.addStudent(student);
        if (isAdded)
            return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("ID already exists"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = studentService.updateStudent(id, student);
        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @GetMapping("/honors")
    public ResponseEntity getHonors() {
        return ResponseEntity.status(200).body(studentService.getHonors());
    }

    @GetMapping("/grad-status/{id}")
    public ResponseEntity checkGradStatus(@PathVariable String id) {
        boolean status = studentService.checkStatusGradition(id);
        return ResponseEntity.status(200).body(new ApiResponse(status ? "Graduated" : "Not graduated Or not found"));
    }

    @GetMapping("/major/{major}")
    public ResponseEntity getByMajor(@PathVariable String major) {

        return ResponseEntity.status(200).body(studentService.getStudentByMajor(major));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity searchByName(@PathVariable String name) {
        Student s = studentService.searchByName(name);
        if (s == null)
            return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
        return ResponseEntity.status(200).body(s);
    }
}
