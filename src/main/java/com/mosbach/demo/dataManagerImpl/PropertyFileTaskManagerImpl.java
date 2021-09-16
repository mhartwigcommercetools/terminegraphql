package com.mosbach.demo.dataManagerImpl;

import com.mosbach.demo.dataManager.TaskManager;
import com.mosbach.demo.model.student.Student;
import com.mosbach.demo.model.task.Task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

public class PropertyFileTaskManagerImpl implements TaskManager {

    String fileName;

    static PropertyFileTaskManagerImpl propertyFileTaskManager = null;

    private PropertyFileTaskManagerImpl(String fileName) {
        this.fileName = fileName;
    }

    static public PropertyFileTaskManagerImpl getPropertyFileTaskManagerImpl(String fileName) {
        if (propertyFileTaskManager == null)
            propertyFileTaskManager = new PropertyFileTaskManagerImpl(fileName);
        return propertyFileTaskManager;
    }


    @Override
    public Collection<Task> getAllTasksByStudent(Student student) {

        List<Task> tasks = new ArrayList<>();
        Properties properties = new Properties();
        int i = 1;
        try {
                properties.load(new FileInputStream(fileName));
                while (properties.containsKey("Task." + i + ".name")) {
                    String studentIdOfTask = properties.getProperty("Task." + i + ".studentid");
                    if (studentIdOfTask == student.getId())
                            tasks.add(
                                new Task(
                                    properties.getProperty("Task." + i + ".id"),
                                    properties.getProperty("Task." + i + ".name"),
                                    properties.getProperty("Task." + i + ".description"),
                                    Integer.parseInt(
                                            properties.getProperty("Task." + i + ".priority")
                                    ),
                                    properties.getProperty("Task." + i + ".studentid")
                            )
                    );
                    i++;
                }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }


    @Override
    public Collection<Task> getAllTasks() {

        List<Task> tasks = new ArrayList<>();
        Properties properties = new Properties();
        int i = 1;
        try {
            properties.load(new FileInputStream(fileName));
            while (properties.containsKey("Task." + i + ".name")) {
                    tasks.add(
                            new Task(
                                    properties.getProperty("Task." + i + ".id"),
                                    properties.getProperty("Task." + i + ".name"),
                                    properties.getProperty("Task." + i + ".description"),
                                    Integer.parseInt(
                                            properties.getProperty("Task." + i + ".priority")
                                    ),
                                    properties.getProperty("Task." + i + ".studentid")
                            )
                    );
                i++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    @Override
    public void addTask(Task task, Student student) {
        Collection<Task> tasks = getAllTasksByStudent(student);
        tasks.add(task);
        storeAllTasks(tasks, student);
    }


    public void storeAllTasks(Collection<Task> tasks, Student student) {

        // Still ignores the student!!! Destroys the file

        Properties properties = new Properties();
        final AtomicLong counter = new AtomicLong();
        counter.set(0);

        tasks.forEach( t -> {
                        properties.setProperty("Task." + counter.incrementAndGet() + ".name", t.getName());
                        properties.setProperty("Task." + counter.get() + ".description", t.getDescription());
                        properties.setProperty("Task." + counter.get() + ".priority", "" + t.getPriority());
        });
        try {
            properties.store(new FileOutputStream(fileName), null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}
