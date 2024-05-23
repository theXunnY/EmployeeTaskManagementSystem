package edu.jsp.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.jsp.entity.Employee;
import edu.jsp.entity.Task;
import edu.jsp.exceptions.EmployeeNotFoundException;
import net.bytebuddy.asm.Advice.Return;

public class Controller {
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("employeeTaskManagement");
	EntityManager manager=factory.createEntityManager();
	EntityTransaction transaction=manager.getTransaction();

	Employee employee=new Employee();
	Task task=new Task();
	

	public Employee saveEmployee(Employee employee) {
		
		if (employee!=null) {
			
			transaction.begin();
			manager.persist(employee);
			transaction.commit();
		}
		return employee;
		
	}
	public Employee saveEmployee(Employee employee, Task task) {
		
		if (employee!=null) {
			List<Employee> employees=new ArrayList<Employee>();
			employees.add(employee);
			task.setEmp(employees);
		
			
			List<Task> tasks=new ArrayList<Task>();
			tasks.add(task);
			employee.setTasks(tasks);
			
			transaction.begin();
			manager.persist(employee);
			manager.persist(task);
			transaction.commit();
		}
		return employee;
		
	}
	
	
	public List<Task> getAlltasks(){
		
		String sql="select s from Task s";
		Query query=manager.createQuery(sql);
		
		List<Task> tasks=(List<Task>) query.getResultList();
		 
		if (tasks!=null) {
			return tasks;
		}
		return null;
	}
	
	
	public List<Employee> getAllEmployees(){

		String sql="select s from Employee s";
		Query query=manager.createQuery(sql);
		
		List<Employee> employees=(List<Employee>) query.getResultList();
		 
		if (employees!=null) {
			return employees;
		}
		return null;
		
	}
	
	
	public Employee searchEmployee(int id) {
		
		Employee employee=manager.find(Employee.class, id);
		if (employee!=null) {
			return employee;
		}
		return null;
	} 
	

	public Employee removeEmployee(int id) {
		Employee employee=manager.find(Employee.class, id);
		
		if (employee!=null) {
			transaction.begin();
			manager.remove(employee);
			transaction.commit();
			System.out.println("EMPLOYEE REMOVED");
		}else {
			System.out.println("SOMETHING WENT WRONGH !!");
		}
		return employee;
		
	}
	
	
	public Task addTask(Task task) {
		
		if (task!=null) {
			transaction.begin();
			manager.persist(task);
			transaction.commit();
			
			return task;
		}
		return null;
	}
	
	
	public Task addTask(Task task, int id) {
		
		if (task!=null) {
			Employee employee=manager.find(Employee.class, id);
			
			if (task!=null && employee!=null) {
				
				List<Employee> employees=new ArrayList<Employee>();
				employees.add(employee);
				task.setEmp(employees);
				
				List<Task> tasks=new ArrayList<Task>();
				tasks.add(task);
				employee.setTasks(tasks);
				
				transaction.begin();
				manager.persist(employee);
				manager.persist(task);
				transaction.commit();
				
				return task;
			}
			
		}
		return null;
	}
	
	
	public Task removeTask(int id) {
		
		Task task=manager.find(Task.class, id);
		if (task!=null) {
			 for (Employee employee : task.getEmp()) {
		            employee.getTasks().remove(task);
		        }
			
			transaction.begin();
			manager.remove(task);
			transaction.commit();
			
			return task;
		} 
		return null;
	}
	
	
	public Task searchTask(int id) {
		
		Task task=manager.find(Task.class, id);
		
		if (task!=null) {
			
			return task;	
			}
		return null;
			
		}
		
		
	public Task assignTask(int tId, int eId) {
		Task task= manager.find(Task.class, tId);
		Employee employee= manager.find(Employee.class, eId);
		
		if (task!=null && employee!=null) {

//		List<Task> tasks=getAlltasks();
//		tasks.add(task);
//		
//		List<Employee> employees=getAllEmployees();
//		employees.add(employee);

			
		task.getEmp().add(employee);
		employee.getTasks().add(task);		
		
		
//		employee.setTasks(tasks);
	
		transaction.begin();
		manager.persist(task);
		manager.persist(employee);
		transaction.commit();

		
		return task;
		}
		return null;
		
		
	}
	
	public Task assignTask(int tId, Employee employee) {
		Task task= manager.find(Task.class, tId);
		
		if (task!=null && employee!=null) {

		List<Task> tasks=new ArrayList<>(); 
		tasks.add(task);
		
		List<Employee> employees=new ArrayList<>(); 
		employees.add(employee);
		
		employee.setTasks(tasks);
		task.setEmp(employees);
		
		transaction.begin();
		manager.persist(employee);
		manager.merge(task);
		transaction.commit();
		
		return task;
		}
		return null;
		
		
	}
	
	
	
	
	public Employee addEmpToTask(int eId, int tId) {
		Employee employee=manager.find(Employee.class, eId);
		Task task=manager.find(Task.class, tId);
		System.out.println(employee);
		System.out.println(task);
		
		if (task!=null && employee!=null) {
			
			
			employee.getTasks().add(task);
			task.getEmp().add(employee);
			
			transaction.begin();
			manager.merge(task);
			manager.merge(employee);
			transaction.commit();
			return employee;
		}
		
		return null;
	}
	
	
	
	
	public Employee addEmpToTask(int eId, Task task) {
		Employee employee=manager.find(Employee.class, eId);
		
		if (task!=null && employee!=null) {
			
			employee.getTasks().add(task);
			task.getEmp().add(employee);
			
			transaction.begin();
			manager.persist(task);
			manager.merge(employee);
			transaction.commit();
			return employee;
		}
		
		return null;
	}
	
	
}
