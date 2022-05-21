package com.compusac.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.compusac.models.entity.ProductDetail;

public interface IProductDetailRepository extends CrudRepository<ProductDetail, Long> {
	public List<ProductDetail> findProductDetailsByProduct(int id);
}
