package com.compusac.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compusac.models.entity.Producto;
import com.compusac.models.repository.IProductoRepository;

@Service
public class ProductServiceImpl implements IProductoService {

	@Autowired
	IProductoRepository productoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoRepository.findAll();
	}

	@Override
	public Producto findById(Long id) {
		return productoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
	}

	@Override
	public Producto create(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return productoRepository.save(producto);
	}

	@Override
	public void delete(Long id) {
		productoRepository.deleteById(id);
	}
}
