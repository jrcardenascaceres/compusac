package com.compusac.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compusac.models.entity.Product;
import com.compusac.models.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	IProductRepository productoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productoRepository.findAll();
	}

	@Override
	public Product findById(Long id) throws NotFoundException {
		return productoRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	public Product create(Product product) {
		return productoRepository.save(product);
	}

	@Override
	public Product update(Product product, Long id) {
		return productoRepository.save(product);
	}

	@Override
	public void delete(Long id) {
		productoRepository.deleteById(id);
	}
	/*
	 * @Override public List<Product> findProductName(String nombre) { return
	 * (List<Product>) productoRepository.findProductName(nombre); }
	 */

	@Override
	public List<Product> findByIdCategory(int id) throws NotFoundException {
		return (List<Product>) productoRepository.findProductsByCategory(id);
	}
}
