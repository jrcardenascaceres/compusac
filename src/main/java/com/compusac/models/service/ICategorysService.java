package com.compusac.models.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.compusac.models.entity.Category;

public interface ICategorysService {
	
	public List<Category> findAll();

	public Category findById(Long id) throws NotFoundException;

	public Category create(Category product);

	public Category update(Category product, Long id);

	public void delete(Long id);

}
