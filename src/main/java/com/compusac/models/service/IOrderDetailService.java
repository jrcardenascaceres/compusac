package com.compusac.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.compusac.models.entity.OrderDetail;

public interface IOrderDetailService {

	public List<OrderDetail> findAll();

	public Optional<OrderDetail> findById(Long id) throws NotFoundException;

	public OrderDetail create(OrderDetail orderDetail);

	public OrderDetail update(OrderDetail orderDetail, Long id);

	public void delete(Long id);
}
