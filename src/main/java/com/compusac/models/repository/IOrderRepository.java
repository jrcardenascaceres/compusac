package com.compusac.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.compusac.models.entity.Order;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Long> {

}
