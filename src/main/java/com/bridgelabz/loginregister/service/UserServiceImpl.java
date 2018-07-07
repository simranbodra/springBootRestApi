package com.bridgelabz.loginregister.service;

import org.springframework.stereotype.Repository;

import com.bridgelabz.loginregister.model.User;
import com.bridgelabz.loginregister.repository.UserRepository;
import com.bridgelabz.loginregister.repository.UserRepositoryImpl;

@Repository
public class UserServiceImpl implements UserService{

	@Override
	public User login(String emailId, String password) {
		UserRepository repository = new UserRepositoryImpl();
		User user = repository.getUser(emailId);
		if(user != null) {
			if(user.getPassword().equals(password)) {
				return user;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	
	@Override
	public boolean register(User user) {
		UserRepository repository = new UserRepositoryImpl();
		User checkUser = repository.getUser(user.getEmailId());
		if(checkUser != null) {
			return false;
		}
		else {
			repository.saveUser(user);
			return true;
		}
	}
}
