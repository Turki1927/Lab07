package org.example.learningmanagementsystemlab07.Service;

import org.example.learningmanagementsystemlab07.Model.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();
    public ArrayList<Student> getStudents() {
        return students;
    }

    public boolean addStudent(Student student) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(student.getId())) {
                return false;
            }
        }
        students.add(student);
        return true;
    }

    public boolean updateStudent(String id, Student updatedStudent) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                students.set(students.indexOf(s), updatedStudent);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                students.remove(s);
                return true;
            }
        }
        return false;
    }
    public String getHonors() {
        StringBuilder firstClass = new StringBuilder("First Class Honor: ");
        StringBuilder secondClass = new StringBuilder("Second Class Honor: ");
        StringBuilder thirdClass = new StringBuilder("Third Class Honor: ");

        for (Student student : students) {
            if (student.getGpa() >= 3.5 && student.getGpa() <= 4.0) {
                firstClass.append(student.getName()).append(", ");
            } else if (student.getGpa() >= 3.0 && student.getGpa() < 3.5) {
                secondClass.append(student.getName()).append(", ");
            } else {
                thirdClass.append(student.getName()).append(", ");
            }
        }

        return  firstClass.toString() + "----------  " +
                secondClass.toString() + "---------" +
                thirdClass.toString();
    }

    public boolean checkStatusGradition(String id) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s.getGraduationDate().isBefore(LocalDate.now());
            }
        }
        return false;
    }

    public ArrayList<Student> getStudentByMajor(String major) {
        ArrayList<Student> sameMajor = new ArrayList<>();
        for (Student s : students) {
            if (s.getMajor().equalsIgnoreCase(major)) {
                sameMajor.add(s);
            }
        }
        return sameMajor;
    }

    public Student searchByName(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }
}