package edu.jsp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Cacheable
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	private String name;
	private LocalDate startDate;
	private LocalDate deadline;
	
	@ManyToMany(mappedBy = "tasks")
	private List<Employee>emp;



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public List<Employee> getEmp() {
		return emp;
	}

	public void setEmp(List<Employee> emp) {
		this.emp = emp;
	}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}


	
	
	
}
