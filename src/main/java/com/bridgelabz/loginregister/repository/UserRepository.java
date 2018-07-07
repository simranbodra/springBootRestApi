package com.bridgelabz.loginregister.repository;

import com.bridgelabz.loginregister.model.User;

public interface UserRepository {

	public User getUser(String emailId);
	public boolean saveUser(User user);
	
}
