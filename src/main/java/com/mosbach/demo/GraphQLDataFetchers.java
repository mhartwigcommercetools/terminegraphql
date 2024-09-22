package com.mosbach.demo;

import com.google.common.collect.ImmutableMap;
import com.mosbach.demo.dataManager.StudentManager;
import com.mosbach.demo.dataManager.TaskManager;
import com.mosbach.demo.dataManagerImpl.PropertyFileStudentManagerImpl;
import com.mosbach.demo.dataManagerImpl.PropertyFileTaskManagerImpl;
import com.mosbach.demo.model.student.Student;
import com.mosbach.demo.model.task.Task;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GraphQLDataFetchers {

    TaskManager taskManager = PropertyFileTaskManagerImpl.getPropertyFileTaskManagerImpl("Tasks.properties");
    StudentManager studentManager = PropertyFileStudentManagerImpl.getPropertyFileStudentManagerImpl("Students.properties");


    private Collection<Task> tasks() {
        return taskManager.getAllTasks();
    };
    private Collection<Student> students() {
        return studentManager.getAllStudents();
    };

    public DataFetcher getTaskByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String taskId = dataFetchingEnvironment.getArgument("id");
            return tasks()
                    .stream()
                    .filter(task -> task.getId().equals(taskId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getTasksByStudentIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String studentId = dataFetchingEnvironment.getArgument("id");
            return tasks()
                    .stream()
                    .filter(task -> task.getStudentId().equals(studentId))
                    .collect(Collectors.toList());
        };
    }

    public DataFetcher getStudentByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String studentId = dataFetchingEnvironment.getArgument("id");
            return students()
                    .stream()
                    .filter(student -> student.getId().equals(studentId))
                    .findFirst()
                    .orElse(null);
        };
    }
    public DataFetcher getStudentDataFetcher() {
        return dataFetchingEnvironment -> {
            Task task = dataFetchingEnvironment.getSource();
            String studentId = task.getStudentId();
            return students()
                    .stream()
                    .filter(student -> student.getId().equals(studentId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getTasksDataFetcher() {
        return dataFetchingEnvironment -> {
            Student student = dataFetchingEnvironment.getSource();
            String studentId = student.getId();
            return tasks()
                    .stream()
                    .filter(task -> task.getStudentId().equals(studentId))
                    .collect(Collectors.toList());
        };
    }
}
