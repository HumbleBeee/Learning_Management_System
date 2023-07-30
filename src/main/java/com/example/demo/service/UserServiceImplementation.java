package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository uRepo;
	
	public UserServiceImplementation(UserRepository uRepo) {
		// TODO Auto-generated constructor stub
		this.uRepo = uRepo;
	}
	
	@Override
	public String addUser(Users user) {
		// TODO Auto-generated method stub
		uRepo.save(user);
		return "User added successfully";
	}

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return uRepo.existsByEmail(email);
	}

	@Override
	public boolean validate(String email, String password) {
		// TODO Auto-generated method stub
		if(uRepo.existsByEmail(email)) {
			Users u = uRepo.getByEmail(email);
			String dbPassword = u.getPassword();
			if(password.equals(dbPassword)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public Users getByEmail(String email) {
		// TODO Auto-generated method stub
		return uRepo.getByEmail(email);
	}

	@Override
	public String getUserRole(String email) {
		// TODO Auto-generated method stub
		Users u = uRepo.getByEmail(email);
		return u.getRole();
	}

	@Override
	public String updateUser(Users user) {
		// TODO Auto-generated method stub
		uRepo.save(user);
		return "Profile updated sucessfully";
	}
	
	
}
