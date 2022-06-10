package com.compusac.models.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.compusac.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.compusac.models.entity.Order;
import com.compusac.models.repository.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderRepository orderRepository;

	@Override
	public List<Order> findAll() {
		return (List<Order>) orderRepository.findAll();
	}

	@Override
	public Order findById(Long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public Order create(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order update(Order order, Long id) {
		return orderRepository.save(order);
	}

	@Override
	public void delete(Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public String generarNumeroOrden() {
		int numero;
		String numeroConcatenado = "";

		List<Order> ordenes = findAll();

		List<Integer> numeros = new ArrayList<>();

		ordenes.forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));

		if (ordenes.isEmpty()) {
			numero = 1;
		} else {
			numero = numeros.stream().max(Integer::compare).get();
			numero++;
		}

		if (numero < 10) {
			numeroConcatenado = "000000000" + String.valueOf(numero);
		} else if (numero < 100) {
			numeroConcatenado = "00000000" + String.valueOf(numero);
		} else if (numero < 1000) {
			numeroConcatenado = "0000000" + String.valueOf(numero);
		} else if (numero < 10000) {
			numeroConcatenado = "0000000" + String.valueOf(numero);
		}

		return numeroConcatenado;
	}

	@Override
	public List<Order> findByUsuario(Usuario usuario) throws NotFoundException {
		return orderRepository.findByUsuario(usuario);
	}
}
