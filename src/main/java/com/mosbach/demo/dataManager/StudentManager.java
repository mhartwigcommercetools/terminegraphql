package com.mosbach.demo.dataManager;

import com.mosbach.demo.model.student.Student;

import java.util.Collection;

public interface StudentManager {

    // getAllStudents, getSpecificStudent, logStudentOn, logStudentOff, ...

    Student getStudentById(String studentID);
    Collection<Student> getAllStudents();
}
