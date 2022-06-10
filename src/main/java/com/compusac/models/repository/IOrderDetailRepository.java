package com.compusac.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.compusac.models.entity.Order;
import com.compusac.models.entity.OrderDetail;
import com.compusac.models.entity.Product;
import com.compusac.models.entity.ProductDetail;

@Repository
public interface IOrderDetailRepository extends CrudRepository<OrderDetail, Long> {
	public List<OrderDetail> findByOrder(Order order);

}
