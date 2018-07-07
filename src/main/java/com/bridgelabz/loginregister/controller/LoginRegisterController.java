package com.bridgelabz.loginregister.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bridgelabz.loginregister.model.User;
import com.bridgelabz.loginregister.service.UserService;
import com.bridgelabz.loginregister.service.UserServiceImpl;
import com.bridgelabz.loginregister.util.Utility;

@RestController
@RequestMapping("/LoginRegister")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LoginRegisterController {

	public static final Logger logger = LoggerFactory.getLogger(LoginRegisterController.class);
	
	@Autowired
	UserService userService = new UserServiceImpl();
	
	// -------------------User Login---------------------------------------------
	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody User checkUser, UriComponentsBuilder ucBuilder) {
		logger.info("Logging User : {}", checkUser);
		
		User user = userService.login(checkUser.getEmailId(), checkUser.getPassword());
        if (user == null) {
            logger.error("User with email {} not found.", checkUser.getEmailId());
            return new ResponseEntity(new Utility("User with email " + checkUser.getEmailId()
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        String message = "Hello, " + user.getUserName() + " Id:- "+ user.getUserId() + " Email:- "
        				+ user.getEmailId() + " Phone Number:- " + user.getPhoneNumber(); 
        return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	// -------------------User Register---------------------------------------------
	@RequestMapping(value = "/register/", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody User checkUser, UriComponentsBuilder ucBuilder){
		logger.info("Register user : {}", checkUser);
		
		boolean registered = userService.register(checkUser);
		if(!registered) {
			logger.error("User with email {} already present.", checkUser.getEmailId());
			return new ResponseEntity(new Utility("User with email " + checkUser.getEmailId()
            + " already present"), HttpStatus.CONFLICT);
		}
		logger.info("User registered with : {}", checkUser.getEmailId());
		String message = "Successfully registired";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
