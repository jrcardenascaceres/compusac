package com.compusac.models.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compusac.models.entity.Product;
import com.compusac.models.entity.Sale;
import com.compusac.models.repository.ISalesRepository;

@Service
public class SalesService implements ISalesService {
	
	@Autowired
	ISalesRepository salesRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Sale> findAll() {
		// TODO Auto-generated method stub
		return (List<Sale>) salesRepository.findAll();
	}

	@Override
	public Sale findById(Long id) throws NotFoundException {
		// TODO Auto-generated method stub
		return salesRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	@Override
	public List<Sale> findByUser(Integer user) {
		return (List<Sale>) salesRepository.findByUser(user);
	}

	@Override
	public Sale create(Sale sale) {
		// TODO Auto-generated method stub
		return salesRepository.save(sale);
	}

	@Override
	public Sale update(Sale sale, Long id) {
		// TODO Auto-generated method stub
		return salesRepository.save(sale);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		salesRepository.deleteById(id);
	}
	
	@Override
	public double total(Integer user) {
		List<Sale> filterSales = this.findByUser(user);
		double total = 0;
		for(Sale sale : filterSales) {
			total += sale.getTotalPrice();
		}
		return total;
	}

}
