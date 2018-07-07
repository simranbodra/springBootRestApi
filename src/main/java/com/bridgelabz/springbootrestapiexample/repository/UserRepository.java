package com.bridgelabz.springbootrestapiexample.repository;

import java.util.List;

import com.bridgelabz.springbootrestapiexample.model.User;

public interface UserRepository {

	public List<User> getUsers();
	public void saveUser(User user);
	public void updateUser(User currentUser);
	public void deleteUserById(int id);
	public void deleteAllUsers();
}
