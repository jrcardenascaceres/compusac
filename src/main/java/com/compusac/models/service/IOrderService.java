package com.compusac.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.compusac.models.entity.Order;

public interface IOrderService {
	public List<Order> findAll();

	public Optional<Order> findById(Long id) throws NotFoundException;

	public Order create(Order order);

	public Order update(Order order, Long id);

	public void delete(Long id);

	public String generarNumeroOrden();
}
