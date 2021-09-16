package com.mosbach.demo.model.task;

import com.mosbach.demo.dataManager.TaskManager;
import com.mosbach.demo.dataManagerImpl.PropertyFileTaskManagerImpl;
import com.mosbach.demo.model.student.Student;

import java.util.Collection;

public class TaskList {
	
	private Student student;
	private Collection<Task> tasks;
	TaskManager taskManager = PropertyFileTaskManagerImpl.getPropertyFileTaskManagerImpl("src/main/resources/Tasks.properties");

	public TaskList() { }

	public TaskList(Student student) {
		this.student = student;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Collection<Task> getTasks() {
		return tasks;
	}

	public void setTasks() {
		tasks = taskManager.getAllTasksByStudent(student);
	}

	public void addTask(Task task) {
		taskManager.addTask(task, student);
	}

}
