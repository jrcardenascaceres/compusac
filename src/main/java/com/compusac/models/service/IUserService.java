package com.compusac.models.service;

import com.compusac.models.entity.User;

public interface IUserService {

	public User findById (Long id);
	
	public User guardar(User user);
}
