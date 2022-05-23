package com.compusac.models.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.compusac.models.entity.Sales;

public interface ISalesService {
	
	public List<Sales> findAll();

	public Sales findById(Long id) throws NotFoundException;

	public Sales create(Sales sale);

	public Sales update(Sales sale, Long id);

	public void delete(Long id);

}
