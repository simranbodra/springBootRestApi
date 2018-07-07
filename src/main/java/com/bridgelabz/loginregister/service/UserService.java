package com.bridgelabz.loginregister.service;

import com.bridgelabz.loginregister.model.User;

public interface UserService {

	public User login(String emailId, String password);
	public boolean register(User user);
}
