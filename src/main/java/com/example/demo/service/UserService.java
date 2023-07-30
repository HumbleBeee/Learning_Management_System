package com.example.demo.service;

import com.example.demo.entity.Users;

public interface UserService {

	public String addUser(Users user);
	public boolean checkEmail(String email);
	public boolean validate(String email, String password);
	Users getByEmail(String email);
	String getUserRole(String email);
	String updateUser(Users user);
	
}
