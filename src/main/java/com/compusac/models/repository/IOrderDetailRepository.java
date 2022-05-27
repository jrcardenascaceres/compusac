package com.compusac.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.compusac.models.entity.OrderDetail;

@Repository
public interface IOrderDetailRepository extends CrudRepository<OrderDetail, Long> {
	
}
