package com.compusac.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.compusac.models.entity.Sale;

public interface ISalesRepository extends CrudRepository<Sale, Long>{
	
}
