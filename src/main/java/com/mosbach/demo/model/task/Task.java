package com.mosbach.demo.model.task;

public class Task {

	private String id = "";
	private String name = "";
	private String description = "";
	private int priority = 0;
	private String studentId = "";


	public String getStudentId() {
		return studentId;
	}


	public Task() {
		
	}

	public String getStudentId(String id) {
		return studentId;
	}

	public Task(String id, String name, String description, int priority, String studentId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.priority = priority;
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getId() {
		return this.id;
	}
}
