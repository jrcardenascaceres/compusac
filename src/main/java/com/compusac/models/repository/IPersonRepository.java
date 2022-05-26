package com.compusac.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.compusac.models.entity.Person;

public interface IPersonRepository extends CrudRepository<Person, Long>{

}
