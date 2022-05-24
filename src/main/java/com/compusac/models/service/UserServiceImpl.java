package com.compusac.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compusac.models.entity.User;
import com.compusac.models.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public User findById(Long id) {
		
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User guardar(User user) {
		
		return userRepository.save(user);
	}

}
