package org.example.learningmanagementsystemlab07.Service;

import org.example.learningmanagementsystemlab07.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses = new ArrayList<>();


    public ArrayList<Course> getCourses() {
        return courses;
    }


    public boolean addCourse(Course course) {
        for (Course c : courses) {
            if (c.getCourseId().equalsIgnoreCase(course.getCourseId())) {
                return false;
            }
        }
        courses.add(course);
        return true;
    }


    public boolean updateCourse(String id, Course updatedCourse) {
        for (Course c : courses) {
            if (c.getCourseId().equalsIgnoreCase(id)) {
                courses.set(courses.indexOf(c), updatedCourse);
                return true;
            }
        }
        return false;
    }


    public boolean deleteCourse(String id) {
        for (Course c : courses) {
            if (c.getCourseId().equalsIgnoreCase(id)) {
                courses.remove(c);
                return true;
            }
        }
        return false;
    }





    public boolean isCourseFull(String courseId) {
        for (Course c : courses) {
            if (c.getCourseId().equalsIgnoreCase(courseId)) {
                return c.getCapacity() <= 0;
            }
        }
        return false;
    }


    public boolean increaseCapacity(int amount, String courseId) {
        for (Course c : courses) {
            if (c.getCourseId().equalsIgnoreCase(courseId)) {
                c.setCapacity(c.getCapacity() + amount);
                return true;
            }
        }
        return false;
    }



    public boolean changeStatusOfCourse(String id) {
        for (Course c : courses) {
            if (c.getCourseId().equalsIgnoreCase(id)) {
                c.setAvailableToRegister(!c.getAvailableToRegister());
                return true;
            }
        }
        return false;
    }








    public boolean changeCourseLocation(String courseId, String newLocation) {
        for (Course c : courses) {
            if (c.getCourseId().equalsIgnoreCase(courseId)) {
                c.setLocation(newLocation);
                return true;
            }
        }
        return false;
    }
}