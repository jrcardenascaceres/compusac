package com.compusac.models.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compusac.models.entity.Usuario;
import com.compusac.models.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public Usuario findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public Usuario guardar(Usuario user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<Usuario> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
