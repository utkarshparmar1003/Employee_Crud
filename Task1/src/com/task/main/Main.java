package com.task.main;

import java.util.Scanner;

import com.task.entity.Employee;
import com.task.service.EmployeeService;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n1. Add Employee\n2. Update Employee\n3. Delete Employee\n4. Get Employee\n5. Exit");
			System.out.print("Choose an option: ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				scanner.nextLine();
				System.out.print("Enter Name: ");
				String name = scanner.nextLine();
				
				System.out.print("Enter Age: ");
				int age = scanner.nextInt();
				System.out.print("Enter Department ID: ");
				int deptId = scanner.nextInt();
				EmployeeService.addEmployee(new Employee(name, age, deptId));
				break;
			case 2:
				System.out.print("Enter Employee ID to update: ");
				int updateId = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Name: ");
				String updateName = scanner.nextLine();
				
				System.out.print("Enter Age: ");
				int updateAge = scanner.nextInt();
				System.out.print("Enter Department ID: ");
				int updateDept = scanner.nextInt();
				EmployeeService.updateEmployeeByPrimaryKey(updateId, updateName, updateAge, updateDept);
				break;
			case 3:
				System.out.print("Enter Employee ID to delete: ");
				int deleteId = scanner.nextInt();
				EmployeeService.deleteEmployeeByPrimaryKey(deleteId);
				break;
			case 4:
				System.out.print("Enter Employee ID to retrieve: ");
				int retrieveId = scanner.nextInt();
				EmployeeService.getEmployeeByPrimaryKey(retrieveId);
				break;
			case 5:
				scanner.close();
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
