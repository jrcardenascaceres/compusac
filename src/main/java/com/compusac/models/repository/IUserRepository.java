package com.compusac.models.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.compusac.models.entity.Usuario;

public interface IUserRepository extends CrudRepository <Usuario,Long> {

	Optional <Usuario> findByUserName(String userName);
	
}
