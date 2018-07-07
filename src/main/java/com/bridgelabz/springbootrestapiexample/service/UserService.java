package com.bridgelabz.springbootrestapiexample.service;

import java.util.List;

import com.bridgelabz.springbootrestapiexample.model.User;

public interface UserService {

	public List<User>findAllUsers();
	public User findById(int id);
	public boolean isUserExist(User user);
	public void saveUser(User user);
	public void updateUser(User currentUser);
	public void deleteUserById(int id);
	public void deleteAllUsers();
}
