package com.bridgelabz.springbootrestapiexample.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.bridgelabz.springbootrestapiexample.model.User;

public class UserRepositoryImpl implements UserRepository{

	/**
	 * Function to establish connection with database and create platform
	 */
	public Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("connection method");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			
		}
		catch(Exception e) {
			System.out.println("Failed to establish Connection");
		}
		return connection;
	}
	
	/**
	 * Function to check user if user is present in the database or not
	 * @param emailId contains the email id entered by the user
	 * @param password contains the password entered by the user
	 * @return user object
	 */
	@Override
	public List<User> getUsers() {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		User user = new User();
		List<User> list = new LinkedList<>();
		
		String query = "SELECT * from User_Database.User";
		try {
			statement = connection.prepareStatement(query);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				user.setId(resultset.getInt(1));
				user.setName(resultset.getString(2));
				user.setAge(resultset.getInt(3));
				user.setSalary(resultset.getInt(4));
				list.add(user);
			}
		} catch (SQLException e) {
			System.out.println("Error in creating platform");
		}
		finally {
			if(resultset != null) {
				try {
					resultset.close();
				}
				catch(SQLException e) {
					System.out.println("Error in result set");
				}
			}
			if(statement != null) {
				try {
					statement.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
		}
		return list;
	}
	
	/**
	 * Function to insert new row in the database
	 * @param id
	 * @param name
	 * @param age
	 * @param salary
	 */
	@Override
	public void saveUser(User user) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		String query = "INSERT INTO User_Database.User (Id, Name, Age, Salary)\n" + 
				"values (?, ?, ?, ?);";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getName());
			statement.setInt(3, user.getAge());
			statement.setInt(4, user.getSalary());
			statement.executeUpdate();
			System.out.println("User registered");
		}
		catch(SQLException e) {
			System.out.println("Error in registering new user");
		}
		finally {
			if(statement != null) {
				try {
					statement.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
		}
	}
	
	@Override
	public void updateUser(User user) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		String query = "UPDATE User_Database.User SET Name = ?, Age = ?, Salary = ? WHERE Id = ?;";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, user.getName());
			statement.setInt(2, user.getAge());
			statement.setInt(3, user.getSalary());
			statement.setInt(4, user.getId());
			statement.executeUpdate();
			System.out.println("User updated");
		}
		catch(SQLException e) {
			System.out.println("Error in updating user");
		}
		finally {
			if(statement != null) {
				try {
					statement.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
		}
	}
	
	@Override
	public void deleteUserById(int id) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		String query = "DELETE FROM User_Database.User WHERE Id = ?;";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			System.out.println("User deleted");
		}
		catch(SQLException e) {
			System.out.println("Error in deleting user");
		}
		finally {
			if(statement != null) {
				try {
					statement.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
		}
	}
	
	@Override
	public void deleteAllUsers() {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		String query = "TRUNCATE User_Database.User;";
		try {
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
			System.out.println("All users deleted");
		}
		catch(SQLException e) {
			System.out.println("Error in deleting all users");
		}
		finally {
			if(statement != null) {
				try {
					statement.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
		}
	}
}
