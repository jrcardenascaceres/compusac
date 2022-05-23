package com.compusac.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compusac.models.entity.Product;
import com.compusac.models.entity.Sales;
import com.compusac.models.repository.ISalesRepository;

@Service
public class SalesService implements ISalesService {
	
	@Autowired
	ISalesRepository salesRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Sales> findAll() {
		// TODO Auto-generated method stub
		return (List<Sales>) salesRepository.findAll();
	}

	@Override
	public Sales findById(Long id) throws NotFoundException {
		// TODO Auto-generated method stub
		return salesRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	public Sales create(Sales sale) {
		// TODO Auto-generated method stub
		return salesRepository.save(sale);
	}

	@Override
	public Sales update(Sales sale, Long id) {
		// TODO Auto-generated method stub
		return salesRepository.save(sale);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		salesRepository.deleteById(id);
	}

}
