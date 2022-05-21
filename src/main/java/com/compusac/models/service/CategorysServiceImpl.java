package com.compusac.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compusac.models.entity.Categorys;
import com.compusac.models.repository.ICategorysRepository;

@Service
public class CategorysServiceImpl implements ICategorysService {
	
	@Autowired
	ICategorysRepository categoryRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Categorys> findAll() {
		return (List<Categorys>) categoryRepository.findAll();
	}

	@Override
	public Categorys findById(Long id) throws NotFoundException {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public Categorys create(Categorys product) {
		return categoryRepository.save(product);
	}

	@Override
	public Categorys update(Categorys product, Long id) {
		// TODO Auto-generated method stub
		return categoryRepository.save(product);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		 categoryRepository.deleteById(id);
	}

}
