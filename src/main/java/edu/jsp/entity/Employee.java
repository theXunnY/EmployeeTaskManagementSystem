package edu.jsp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Cacheable
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@SequenceGenerator(name="my-sql")
	private int id;
	private String name;
	private int age;
	
	@CreationTimestamp
	private LocalDate DOJ;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	private double salary;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(joinColumns = @JoinColumn(name="emp_id"),inverseJoinColumns = @JoinColumn(name = "task_id"))
	private List<Task>tasks;


	public void setId(int id) {
		this.id = id;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getDOJ() {
		return DOJ;
	}

	public void setDOJ(LocalDate dOJ) {
		DOJ = dOJ;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	 
	
}
