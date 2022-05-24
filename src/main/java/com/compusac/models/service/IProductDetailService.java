package com.compusac.models.service;

import java.util.List;

import com.compusac.models.entity.ProductDetail;

public interface IProductDetailService {

	public List<ProductDetail> findAll();

	public ProductDetail findById(Long id);

	public ProductDetail create(ProductDetail productDetail);

	public ProductDetail update(ProductDetail productDetail, Long id);

	public void delete(Long id);

	public List<ProductDetail> findProductDetailsByProduct(int id);
	
	public List<ProductDetail> findProductDetailsByCategory(int category, int idProduct);
}
