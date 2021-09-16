package com.mosbach.demo.dataManager;

import com.mosbach.demo.model.student.Student;
import com.mosbach.demo.model.task.Task;

import java.util.Collection;

public interface TaskManager {

    Collection<Task> getAllTasksByStudent(Student student);
    Collection<Task> getAllTasks();
    void addTask(Task task, Student student);

    // TODO
    // removeTask, getTasksInOrder, getTaskByTaskID, ...

    // TODO
    // Make the TaskManager handling students.

}
