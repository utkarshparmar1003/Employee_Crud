package com.task.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.task.connection.Connectivity;
import com.task.entity.Employee;

public class EmployeeService {
	public static void addEmployee(Employee emp) throws ClassNotFoundException {
		String sql = "INSERT INTO employee (name, age, department_id) VALUES (?, ?, ?)";
		try (Connection conn = Connectivity.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, emp.name);
			stmt.setInt(2, emp.age);
			stmt.setInt(3, emp.departmentId);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				System.out.println("Employee added with ID: " + rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateEmployeeByPrimaryKey(int id, String name, int age, int departmentId) throws ClassNotFoundException {
		String checkSql = "SELECT id FROM employee WHERE id = ?";
		String updateSql = "UPDATE employee SET name = ?, age = ?, department_id = ? WHERE id = ?";
		String insertSql = "INSERT INTO employee (id, name, age, department_id) VALUES (?, ?, ?, ?)";
		try (Connection conn = Connectivity.getConnection();
				PreparedStatement checkStmt = conn.prepareStatement(checkSql);
				PreparedStatement updateStmt = conn.prepareStatement(updateSql);
				PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
			checkStmt.setInt(1, id);
			ResultSet rs = checkStmt.executeQuery();
			if (rs.next()) {
				updateStmt.setString(1, name);
				updateStmt.setInt(2, age);
				updateStmt.setInt(3, departmentId);
				updateStmt.setInt(4, id);
				updateStmt.executeUpdate();
				System.out.println("Employee updated with ID: " + id);
			} else {
				insertStmt.setInt(1, id);
				insertStmt.setString(2, name);
				insertStmt.setInt(3, age);
				insertStmt.setInt(4, departmentId);
				insertStmt.executeUpdate();
				System.out.println("Employee created with ID: " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteEmployeeByPrimaryKey(int id) throws ClassNotFoundException {
		String sql = "DELETE FROM employee WHERE id = ?";
		try (Connection conn = Connectivity.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Employee deleted with ID: " + id);
			} else {
				System.out.println("No employee found with ID: " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void getEmployeeByPrimaryKey(int id) throws ClassNotFoundException {
		String sql = "SELECT * FROM employee WHERE id = ?";
		try (Connection conn = Connectivity.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("Employee Found: " + rs.getString("name") + ", Age: " + rs.getInt("age")
						+ ", Department ID: " + rs.getInt("department_id"));
			} else {
				System.out.println("No employee found with ID: " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
