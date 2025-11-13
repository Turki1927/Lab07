package org.example.learningmanagementsystemlab07.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.learningmanagementsystemlab07.Api.ApiResponse;
import org.example.learningmanagementsystemlab07.Model.Course;
import org.example.learningmanagementsystemlab07.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;


    @GetMapping("/get")
    public ResponseEntity getCourses() {
        return ResponseEntity.status(200).body(courseService.getCourses());
    }




    @PostMapping("/add")
    public ResponseEntity<?> addCourse( @RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isAdded = courseService.addCourse(course);
        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Course added successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Course ID already exists"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable String id,  @RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = courseService.updateCourse(id, course);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable String id) {
        boolean isDeleted = courseService.deleteCourse(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
        }
    }

    @GetMapping("/is-full/{id}")
    public ResponseEntity isCourseFull(@PathVariable String id) {
        boolean isFull = courseService.isCourseFull(id);
        return ResponseEntity.status(200).body(new ApiResponse(isFull ? "Course is full" : "Course still has seats"));
    }

    @PutMapping("/increase/{id}/{amount}")
    public ResponseEntity increaseCapacity(@PathVariable String id, @PathVariable int amount) {
        boolean isIncreased = courseService.increaseCapacity(amount, id);
        if (isIncreased) {
            return ResponseEntity.status(200).body(new ApiResponse("Course capacity increased successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
        }
    }

    @PutMapping("/change-status/{id}")
    public ResponseEntity changeStatus(@PathVariable String id) {
        boolean isChanged = courseService.changeStatusOfCourse(id);
        if (isChanged) {
            return ResponseEntity.status(200).body(new ApiResponse("Course status changed successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
        }
    }

    @PutMapping("/change-location/{id}/{location}")
    public ResponseEntity changeLocation(@PathVariable String id, @PathVariable String location) {
        boolean isChanged = courseService.changeCourseLocation(id, location);
        if (isChanged) {
            return ResponseEntity.status(200).body(new ApiResponse("Course location updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
        }
    }
}