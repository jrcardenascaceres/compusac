package com.compusac.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.compusac.models.entity.User;

public interface IUserRepository extends CrudRepository <User,Long> {

}
