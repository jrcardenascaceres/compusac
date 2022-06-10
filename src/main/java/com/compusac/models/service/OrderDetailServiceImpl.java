package com.compusac.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.compusac.models.entity.Order;
import com.compusac.models.entity.OrderDetail;
import com.compusac.models.entity.ProductDetail;
import com.compusac.models.repository.IOrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

	@Autowired
	IOrderDetailRepository detailRepository;

	@Override
	public List<OrderDetail> findAll() {
		return (List<OrderDetail>) detailRepository.findAll();
	}

	@Override
	public OrderDetail findById(Long id)  {
		return detailRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Detalle de orden invalido con Id:" + id));
	}

	@Override
	public OrderDetail create(OrderDetail orderDetail) {
		return detailRepository.save(orderDetail);
	}

	@Override
	public OrderDetail update(OrderDetail orderDetail, Long id) {
		return detailRepository.save(orderDetail);
	}

	@Override
	public void delete(Long id) {
		detailRepository.deleteById(id);
	}

	@Override
	public List<OrderDetail> findProductDetailsByOrder(Order order) {
		return detailRepository.findByOrder(order);
	}

}
