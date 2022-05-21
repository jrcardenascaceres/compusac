package com.compusac.models.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.compusac.models.entity.Categorys;

public interface ICategorysService {
	
	public List<Categorys> findAll();

	public Categorys findById(Long id) throws NotFoundException;

	public Categorys create(Categorys product);

	public Categorys update(Categorys product, Long id);

	public void delete(Long id);

}
