package com.compusac.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.compusac.models.entity.Product;

public interface IProductRepository extends CrudRepository<Product, Long> {
	// public List<Product> findProductName(String nombre);

	public List<Product> findProductsByCategory(int id);
}
