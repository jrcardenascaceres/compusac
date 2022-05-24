package com.compusac.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compusac.models.entity.Person;
import com.compusac.models.repository.IPersonRepository;

@Service
public class PersonServiceImpl implements IPersonService{

	@Autowired
	private IPersonRepository personRepository;
	
	@Override
	public Person findById(Long id) {
		
		return personRepository.findById(id).orElse(null);
	}

	@Override
	public Person guardar(Person person) {

		return personRepository.save(person); 
	}

}
