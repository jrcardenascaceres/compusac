package com.compusac.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.compusac.models.entity.Order;
import com.compusac.models.entity.OrderDetail;
import com.compusac.models.entity.Product;
import com.compusac.models.entity.ProductDetail;

public interface IOrderDetailService {

	public List<OrderDetail> findAll();

	public OrderDetail findById(Long id) throws NotFoundException;

	public OrderDetail create(OrderDetail orderDetail);

	public OrderDetail update(OrderDetail orderDetail, Long id);
	
	public List<OrderDetail> findProductDetailsByOrder(Order order);

	public void delete(Long id);
}
