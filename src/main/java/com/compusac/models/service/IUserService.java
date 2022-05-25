package com.compusac.models.service;

import java.util.Optional;

import com.compusac.models.entity.Usuario;

public interface IUserService {

	public Usuario findById (Long id);
	
	public Usuario guardar(Usuario user);
	
	Optional<Usuario> findByUserName(String userName);
}
