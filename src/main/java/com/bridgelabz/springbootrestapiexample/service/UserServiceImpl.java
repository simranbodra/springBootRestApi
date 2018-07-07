package com.bridgelabz.springbootrestapiexample.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bridgelabz.springbootrestapiexample.model.User;
import com.bridgelabz.springbootrestapiexample.repository.UserRepository;
import com.bridgelabz.springbootrestapiexample.repository.UserRepositoryImpl;

@Repository
public class UserServiceImpl implements UserService{
	
	UserRepository repository = new UserRepositoryImpl();
	List<User> list = new LinkedList<>();
	public UserServiceImpl(){
		list = repository.getUsers();
	}

	@Override
	public List<User>findAllUsers(){
		return list;
	}
	
	@Override
	public User findById(int id) {
		User user = new User();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getId() == id) {
				user = list.get(i);
			}
		}
		return user;
	}
	
	@Override
	public boolean isUserExist(User user) {
		boolean value = false;
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getId() == user.getId()) {
				value = true;
			}
		}
		return value;
	}
	
	@Override
	public void saveUser(User user) {
		repository.saveUser(user);
	}
	
	@Override
	public void updateUser(User currentUser) {
		repository.updateUser(currentUser);
	}
	
	@Override
	public void deleteUserById(int id) {
		repository.deleteUserById(id);
	}
	
	@Override
	public void deleteAllUsers() {
		repository.deleteAllUsers();
	}
}
