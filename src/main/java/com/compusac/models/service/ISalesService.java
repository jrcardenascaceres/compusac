package com.compusac.models.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.compusac.models.entity.Sale;

public interface ISalesService {
	
	public List<Sale> findAll();

	public Sale findById(Long id) throws NotFoundException;

	public Sale create(Sale sale);

	public Sale update(Sale sale, Long id);

	public void delete(Long id);

	public List<Sale> findByUser(Integer user);

	double total(Integer user);

}
