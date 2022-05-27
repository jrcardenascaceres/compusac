package com.compusac.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compusac.models.entity.Category;
import com.compusac.models.repository.ICategorysRepository;

@Service
public class CategorysServiceImpl implements ICategorysService {
	
	@Autowired
	ICategorysRepository categoryRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Category> findAll() {
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public Category findById(Long id) throws NotFoundException {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public Category create(Category product) {
		return categoryRepository.save(product);
	}

	@Override
	public Category update(Category product, Long id) {
		// TODO Auto-generated method stub
		return categoryRepository.save(product);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		 categoryRepository.deleteById(id);
	}

}
