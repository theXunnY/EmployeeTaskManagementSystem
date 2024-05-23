package edu.jsp.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edu.jsp.controller.Controller;
import edu.jsp.entity.Employee;
import edu.jsp.entity.Task;
import edu.jsp.exceptions.EmployeeNotFoundException;

public class View {

	boolean loop=true;
	Scanner scanner =new Scanner(System.in);
	Controller controller=new Controller();
	
	public static void main(String[] args) {
		View view=new View();
		while (view.loop) {
			view.mainView();
			
		}
		
	}
	
	private void mainView() {
		
		System.out.println("---------------------WELCOME-----------------\n"
				+ "\n\nENTER YOUR CHOICE \n"
				+ "1.	SAVE EMPLOYEE\n"
				+ "2.	SEARCH AN EMPLOYEE\n"
				+ "3.	GET ALL EMPLOYEE\n"
				+ "4.	REMOVE EMPLOYEE\n"
				+ "5.	ADD TASK\n"
				+ "6.	REMOVE TASK\n"
				+ "7.	GET ALL TASKS\n"
				+ "8.	SEARCH TASK\n"
				+ "9.	ASSIGN TASK\n"
				+ "10.	ADD EMPLOYEE TO TASK\n"
				+ "11.	EXIT");
		
		System.out.println();
		try{
			int choice=scanner.nextInt();
		
		
		switch (choice) {
		case 1:{
			saveEmp();
			break;
		}
		case 2:{
			searchEmp();
			break;
		}
		case 3:{
			getAllEmplyees();
			break;
		}
		case 4:{
			removeEmp();
			break;
		}
		case 5:{
			addTask();
			break;
		}
		case 6:{
			removeTask();
			break;
		}
		case 7:{
			getAllTasks();
			break;
		}
		case 8:{
			searchTasks();
			break;
		}
		case 9:{
			assignTask();
			break;
		}
		case 10:{
			addEmpToTask();
			break;
		}
		case 11: {
            loop = false; 
            System.out.println("Exiting application...");
            break;
        }
		default:
			System.out.println("INVALID CHOICE");
			break;
		}}catch (Exception e) {
			loop=false;
			System.out.println("please elter numbers only");
			
		}
		}

	private void addEmpToTask() {
		
		System.out.println("ADD EMPLOYEE TO TASK :\n"
				+ "1.	ADD EXISTING EMPLOYEE FOR A TASK\n"
				+ "2.	ADD NEW EMPLOYEE FOR A TASK\n"
				+ "3.	EXIT.");
		
		int choice=scanner.nextInt();
		
		switch (choice) {
		case 1:{
			
			getAllEmplyees();
			System.out.println("ENTER EMPLOYEE ID TO ASSIGN TASK : ");
			int eId=scanner.nextInt();
			
			getAllTasks();
			System.out.println("GREATT....\n");

			System.out.println("\nNOW ENTER TASK ID TO ASSIGN THAT TASK TO EMPLOYEE ....\n");
			int tId=scanner.nextInt();
			
			
			if (controller.addEmpToTask(eId, tId)!=null) {
				System.out.println("DONE");
			}
			
			break;
		}
		case 2:{
			getAllEmplyees();
			
			System.out.println("ENTER EMPLOYEE ID TO ASSIGN TASK : ");
			int eId=scanner.nextInt();
			
			System.out.println("\nMAKE TASK FOR THIS EMPLOYEE : \n");
			
			System.out.println("ENTER TASK NAME : ");
			String tname=scanner.next();
			scanner.nextLine();
			
			System.out.println("TASK START DATE IS FROM NOW : ");
			LocalDate startDate=LocalDate.now();
			System.out.println(startDate);
			scanner.nextLine();
			
			System.out.println("TASK DEADLINE IS 10 DAYS FROM NOW : ");
			LocalDate deadline=LocalDate.now().plusDays(10);
			System.out.println(deadline);;
			scanner.nextLine();
			
			Task task =new Task();
			task.setName(tname);
			task.setStartDate(startDate);
			task.setDeadline(deadline);
			
			if(controller.addEmpToTask(eId, task)!=null) {
				System.out.println("TASK GIVEN ");
			}else {
				System.out.println("SOMETHING WENT WRING");
			}
			
			break;
		}
		case 3:{
			break;
		}
		default:
			break;
		}
		
		
	}

	private void assignTask() {
		System.out.println("ASSIGN TASK TO :\n"
				+ "1.	EXISTING EMPLOYEE\n"
				+ "2.	NEW EMPLOYEE\n"
				+ "3.	EXIT.");
		
		int choice=scanner.nextInt();
		
		switch (choice) {
		case 1:{
			
			getAllTasks();
			System.out.println("ENTER TASK ID TO ASSIGN: ");
			int tId=scanner.nextInt();
			
			System.out.println("GREATT....\n");
			getAllEmplyees();
			System.out.println("\nNOW ENTER EMPLOYEE ID TO ASSIGN THAT TASK....\n");
			int eId=scanner.nextInt();
			
			Task task= controller.assignTask(tId, eId);
			if (task!=null) {
				System.out.println("TASK ASSIGNED SUCCESFULLY");
			}
			
			break;
		}
		case 2:{
			
			getAllTasks();
			System.out.println("ENTER TASK ID TO ASSIGN: ");
			int tId=scanner.nextInt();
			
			System.out.println("NOW LET'S ENTER NEW EMPLOYEE DETAILS :\n");
			
			System.out.println("ENTER EMPLOYEE NAME : ");
			String name=scanner.next();
			scanner.nextLine();
			
			System.out.println("ENTER EMPLOYEE AGE : ");
			int age=scanner.nextInt();
			scanner.nextLine();
			
			
			System.out.println("ENTER EMPLOYEE SALARY : ");
			double salary=scanner.nextDouble();
			scanner.nextLine();
			
			System.out.println("DATE OF JOINING IS NOW.");
			LocalDate DOJ=LocalDate.now();
			scanner.nextLine();
			
			Employee employee=new Employee();
			employee.setName(name);
			employee.setAge(age);
			employee.setDOJ(DOJ);
			employee.setSalary(salary);
			
			controller.assignTask(tId, employee);
			
			break;
		}
		case 3:{
			break;
		}
		default:
			break;
		}
		
	}

	private void searchTasks() {
		System.out.println("ENTER TASK ID TO SEARCH ");
		int id=scanner.nextInt();
		
		Task task=controller.searchTask(id);
		
		if (task!=null) {
			System.out.println("TASKk ID : "+ task.getId());
			System.out.println("TASK NAME : " + task.getName());
			System.out.println("TASK START DATE: " + task.getStartDate());
			
			
			if (!(task.getEmp().isEmpty())) {
				System.out.println("TASK ASSIGNED TO : ");
			for (Employee employee : task.getEmp()) {
				System.out.println("EMPLOYEE ID : "+ employee.getId());
				System.out.println("EMPLOYEE NAME : " + employee.getName());
				System.out.println("EMPLOYEE AGE : " + employee.getAge());
				System.out.println("EMPLOYEE DATE OF JOINING : " +employee.getDOJ());
				System.out.println("EMPLOYEE SALARY : " + employee.getSalary());
				System.out.println("----------------------------------------------\n");
			}
			}
			else {
				System.out.println("THIS TASK IS NOT ASSIGNED TO ANY EMPLOYEE..");
			}
//			
		} else {
			System.out.println("SOMETHING WENT WRONG !!");
		}
		
	}

	private void getAllTasks() {
		
		if (controller.getAlltasks()!=null) {
			List<Task> tasks=controller.getAlltasks(); 
			
			for (Task task :tasks) {
				System.out.println("TASk ID : "+ task.getId());
				System.out.println("TASK NAME : " + task.getName());
				System.out.println("TASK START DATE: " + task.getStartDate());
				System.out.println("TASK END DATE : " + task.getDeadline());
//				System.out.println("EMPLOYEE SALARY : " + employee.getSalary());
				System.out.println("----------------------------------------------\n");
				
			}
		}
		else {
			System.out.println("SOMETHING WENT WRONG !!");
			controller.getAllEmployees();
		}
		
		
	}

	private void removeTask() {
		getAllTasks();
		
		System.out.println("ENTER TASK ID TO REMOVE ");
		int id=scanner.nextInt();
		Task task= controller.removeTask(id);
		
		if (task!=null) {
			System.out.println("TASK REMOVED");
		} else {
			System.out.println("SOMETHING WENT WRONG !!");
		}
		
	}

	private void addTask() {
		System.out.println("ENTER TASK NAME : ");
		String tname=scanner.next();
		scanner.nextLine();
		
		System.out.println("TASK START DATE IS FROM NOW : ");
		LocalDate startDate=LocalDate.now();
		System.out.println(startDate);
		scanner.nextLine();
		
		System.out.println("TASK DEADLINE IS 10 DAYS FROM NOW : ");
		LocalDate deadline=LocalDate.now().plusDays(10);
		System.out.println(deadline);;
		scanner.nextLine();
		
		Task task =new Task();
		task.setName(tname);
		task.setStartDate(startDate);
		task.setDeadline(deadline);
		
		List<Task> tasks=new ArrayList<Task>();
		tasks.add(task);
		
		Task target = controller.addTask(task);
		
		if (target!=null) {
			System.out.println("TASKED SET ");
		}
		
		System.out.println("WANT TO ASSIGN THIS TASK TO AN EMPLOYEE?\n"
				+ "1.	YES TO EXESTING EMPLOYEE\n"
				+ "2.	TO A NEW EMPLOYEE\n"
				+ "3.	NO..THANK YOU.");
		int choice=scanner.nextInt();
		
		switch (choice) {
		case 1:{
			getAllEmplyees();
			System.out.println("\nENTER EMPLOYEE ID TO ASSIGN THIS TASK");
			int id=scanner.nextInt();
			if (target!=null) {
				controller.addTask(task, id);
				System.out.println("TASK SET FOR EMPLOYEE ID :"+ id);
			}
			break;
		}
		case 2:{
			System.out.println("ENTER EMPLOYEE NAME : ");
			String name=scanner.next();
			scanner.nextLine();
			
			System.out.println("ENTER EMPLOYEE AGE : ");
			int age=scanner.nextInt();
			scanner.nextLine();
			
			
			System.out.println("ENTER EMPLOYEE SALARY : ");
			double salary=scanner.nextDouble();
			scanner.nextLine();
			
			System.out.println("DATE OF JOINING IS NOW.");
			LocalDate DOJ=LocalDate.now();
			scanner.nextLine();
			
			Employee employee=new Employee();
			employee.setName(name);
			employee.setAge(age);
			employee.setDOJ(DOJ);
			employee.setSalary(salary);
			
			List<Employee> employees=new ArrayList<Employee>();
			employees.add(employee);	
			 
			task.setEmp(employees);
			employee.setTasks(tasks);
			
			controller.saveEmployee(employee);
			System.out.println("TASK ASSIGNED TO EMPLOYEE");
			break;
		}
		case 3:{
			break;
		}
		default:
			break;
		}
	
		
	}


	
	
	private void removeEmp() {
		getAllEmplyees();
		
		System.out.println("ENTER EMPLOYEE ID TO REMOVE ");
		int id=scanner.nextInt();
		controller.removeEmployee(id);
	}
	
	
	
	

	private void getAllEmplyees() {
		if (controller.getAllEmployees()!=null) {
			 List<Employee> employees = controller.getAllEmployees();
//			 List<Task> tasks=controller.getAlltasks()
			for (Employee employee : employees) {
				System.out.println("EMPLOYEE ID : "+ employee.getId());
				System.out.println("EMPLOYEE NAME : " + employee.getName());
				System.out.println("EMPLOYEE AGE : " + employee.getAge());
				System.out.println("EMPLOYEE DATE OF JOINING : " +employee.getDOJ());
				System.out.println("EMPLOYEE SALARY : " + employee.getSalary());
				System.out.println("----------------------------------------------\n");
			}
		}
		else {
			System.out.println("SOMETHING WENT WRONG !!");
			controller.getAllEmployees();
		}
		
	}

	public void saveEmp() {
		System.out.println("ENTER EMPLOYEE NAME : ");
		String name=scanner.next();
		scanner.nextLine();
		
		System.out.println("ENTER EMPLOYEE AGE : ");
		int age=scanner.nextInt();
		scanner.nextLine();
		
		
		System.out.println("ENTER EMPLOYEE SALARY : ");
		double salary=scanner.nextDouble();
		scanner.nextLine();
		
		System.out.println("DATE OF JOINING IS NOW.");
		LocalDate DOJ=LocalDate.now();
		scanner.nextLine();
		
		Employee employee=new Employee();
		employee.setName(name);
		employee.setAge(age);
		employee.setDOJ(DOJ);
		employee.setSalary(salary);
		
		List<Employee> employees=new ArrayList<Employee>();
		employees.add(employee);	
		
		
		System.out.println("WANT TO ASSIGN A TASK TO THIS EMPLOYEE?\n"
				+ "1.	YES\n"
				+ "2.	NO..THAKYOU");
		
		int choice=scanner.nextInt();
		switch (choice) {
		case 1:{
			System.out.println("ENTER TASK NAME : ");
			String tname=scanner.next();
			scanner.nextLine();
			
			System.out.println("TASK START DATE IS FROM NOW : ");
			LocalDate startDate=LocalDate.now();
			System.out.println(startDate);
			scanner.nextLine();
			
			System.out.println("TASK DEADLINE IS 10 DAYS FROM NOW : ");
			LocalDate deadline=LocalDate.now().plusDays(10);
			System.out.println(deadline);;
			scanner.nextLine();
			
			Task task =new Task();
			task.setName(tname);
			task.setStartDate(startDate);
			task.setDeadline(deadline);
			
			List<Task> tasks=new ArrayList<Task>();
			tasks.add(task);
			employee.setTasks(tasks);
			task.setEmp(employees);
			
			controller.saveEmployee(employee, task);
			
			break;
		}
		case 2:{
			controller.saveEmployee(employee);
			Task task=new Task();
			task.setEmp(employees);
			break;
		}
		default:
			break;
		}
		
		
	}

	
	public void searchEmp() {
		System.out.println("ENTER EMPLOYEE ID : ");
		int id=scanner.nextInt();
		Employee employee= controller.searchEmployee(id);
		
		if (employee!=null) {
	        System.out.println("\nSearch results : ");
	            System.out.println("EMPLOYEE ID : " + employee.getId());
	            System.out.println("EMPLOYEE NAME : " + employee.getName());
	            System.out.println("EMPLOYEE AGE : " + employee.getAge());
	            System.out.println("EMPLOYEE DATE OF JOINING : " + employee.getDOJ());
	            System.out.println("EMPLOYEE SALARY : " + employee.getSalary());
	        }
	     else {
	        System.out.println("EMPLOYEE NOT FOUND !!");
	    }
		
	}



	














































}
