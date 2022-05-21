package com.compusac.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.compusac.models.entity.Product;

public interface IProductRepository extends CrudRepository<Product, Long> {

}
