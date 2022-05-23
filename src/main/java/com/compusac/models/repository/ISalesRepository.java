package com.compusac.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.compusac.models.entity.Sales;

public interface ISalesRepository extends CrudRepository<Sales, Long>{
	
}
