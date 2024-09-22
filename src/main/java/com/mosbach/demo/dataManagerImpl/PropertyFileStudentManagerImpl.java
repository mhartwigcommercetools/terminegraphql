package com.mosbach.demo.dataManagerImpl;

import com.mosbach.demo.dataManager.StudentManager;
import com.mosbach.demo.dataManager.TaskManager;
import com.mosbach.demo.model.student.Student;
import com.mosbach.demo.model.task.Task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

public class PropertyFileStudentManagerImpl implements StudentManager {

    String fileName;

    static PropertyFileStudentManagerImpl propertyFileStudentManager = null;

    private PropertyFileStudentManagerImpl(String fileName) {
        this.fileName = fileName;
    }

    static public PropertyFileStudentManagerImpl getPropertyFileStudentManagerImpl(String fileName) {
        if (propertyFileStudentManager == null)
            propertyFileStudentManager = new PropertyFileStudentManagerImpl(fileName);
        return propertyFileStudentManager;
    }


    @Override
    public Student getStudentById(String studentID) {

        Properties properties = new Properties();
        int i = 1;
        Student student = null;

        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try(InputStream resourceStream = loader.getResourceAsStream(fileName)) {
                properties.load(resourceStream);
            }

            while (properties.containsKey("Student." + i + ".firstname")) {

                    if (properties.getProperty("Student." + i + ".ID").equals(studentID)) {
                       student = new Student(
                               studentID,
                               properties.getProperty("Task." + i + ".firstname"),
                               properties.getProperty("Task." + i + ".lastname"));
                    }
                    i++;
                }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }


    @Override
    public Collection<Student> getAllStudents() {
        Properties properties = new Properties();
        int i = 1;
        List<Student> students = new ArrayList<>();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try(InputStream resourceStream = loader.getResourceAsStream(fileName)) {
                properties.load(resourceStream);
            }

            while (properties.containsKey("Student." + i + ".firstname")) {
                students.add(
                        new Student(
                            properties.getProperty("Student." + i + ".id"),
                            properties.getProperty("Student." + i + ".firstname"),
                            properties.getProperty("Student." + i + ".lastname"))
                    );
                i++;
                }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return students;

    }

}
