package org.example.learningmanagementsystemlab07.Service;

import org.example.learningmanagementsystemlab07.Model.Assignment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssignmentService {

    ArrayList<Assignment> assignments = new ArrayList<>();


    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }


    public boolean addAssignment(Assignment assignment) {
        for (Assignment a : assignments) {
            if (a.getAssignmentId().equalsIgnoreCase(assignment.getAssignmentId())) {
                return false;
            }
        }
        assignments.add(assignment);
        return true;
    }


    public boolean updateAssignment(String id, Assignment updatedAssignment) {
        for (Assignment a : assignments) {
            if (a.getAssignmentId().equalsIgnoreCase(id)) {
                assignments.set(assignments.indexOf(a), updatedAssignment);
                return true;
            }
        }
        return false;
    }


    public boolean deleteAssignment(String id) {
        for (Assignment a : assignments) {
            if (a.getAssignmentId().equalsIgnoreCase(id)) {
                assignments.remove(a);
                return true;
            }
        }
        return false;
    }


    public ArrayList<Assignment> getSubmitted() {
        ArrayList<Assignment> submittedList = new ArrayList<>();
        for (Assignment a : assignments) {
            if (a.getIsSubmitted() != null && a.getIsSubmitted()) {
                submittedList.add(a);
            }
        }
        return submittedList;
    }


    public boolean extendDeadline(String id) {
        for (Assignment a : assignments) {
            if (a.getAssignmentId().equalsIgnoreCase(id)) {
                a.setDeadline(a.getDeadline().plusDays(1));
                return true;
            }
        }
        return false;
    }



    public boolean submitAssignment(String id) {
        for (Assignment a : assignments) {
            if (a.getAssignmentId().equalsIgnoreCase(id)) {
                a.setIsSubmitted(true);
                return true;
            }
        }
        return false;
    }


    public Assignment checkAssignment(String id) {
        for (Assignment a : assignments) {
            if (a.getAssignmentId().equalsIgnoreCase(id)) {
                return a;
            }
        }
        return null;
    }
}







