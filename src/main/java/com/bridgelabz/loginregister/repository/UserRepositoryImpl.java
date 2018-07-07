package com.bridgelabz.loginregister.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bridgelabz.loginregister.model.User;

public class UserRepositoryImpl implements UserRepository{

	/**
	 * Function to establish connection with database and create platform
	 */
	public Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
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
	public User getUser(String emailId) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultset = null;
		User user = new User();
		String query = "SELECT * from User_Database.User_Information where Email_Id = ?;";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, emailId);
			resultset = statement.executeQuery();
			if(resultset.next()) {
				user.setUserId(resultset.getInt(1));
				user.setUserName(resultset.getString(2));
				user.setEmailId(resultset.getString(3));
				user.setPassword(resultset.getString(4));
				user.setPhoneNumber((long) resultset.getDouble(5));
			}
			else {
				user = null;
				System.out.println("Invalid User");
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
		System.out.println(user);
		return user;
	}
	
	/**
	 * Function to insert new row in the database
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param phoneNumber
	 */
	@Override
	public boolean saveUser(User user) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		boolean value = false;
		String query = "INSERT INTO User_Database.User_Information (User_Id, Name, Email_Id, Password, PhoneNumber)\n" + 
				"values (?, ?, ?, ?, ?);";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, 0);
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getEmailId());
			statement.setString(4, user.getPassword());
			statement.setString(5, ""+user.getPhoneNumber());
			statement.executeUpdate();
			System.out.println("User registered");
			value = true;
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
		return value;
	}
}
