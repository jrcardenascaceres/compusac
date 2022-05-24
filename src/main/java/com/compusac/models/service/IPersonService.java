package com.compusac.models.service;

import com.compusac.models.entity.Person;

public interface IPersonService {

	public Person findById (Long id);
	
	public Person guardar(Person person);
	
}
