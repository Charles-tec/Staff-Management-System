package com.engineering.service;

import com.engineering.bean.User;
import com.engineering.generic.GenericService;

public interface UserService extends GenericService<User> {

	boolean authenticate(String email, String password);
	
	User findByEmail(String email);
	
}
