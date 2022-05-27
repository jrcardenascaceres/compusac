package com.compusac.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compusac.models.entity.Product;
import com.compusac.models.entity.ProductDetail;
import com.compusac.models.repository.IProductDetailRepository;

@Service
public class IProductDetailServiceImpl implements IProductDetailService {

	@Autowired
	IProductDetailRepository detailRepository;

	@Override
	public List<ProductDetail> findAll() {
		return (List<ProductDetail>) detailRepository.findAll();
	}

	@Override
	public ProductDetail findById(Long id) {
		return detailRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Detalle de producto invalido con Id:" + id));
	}

	@Override
	public ProductDetail create(ProductDetail productDetail) {
		return detailRepository.save(productDetail);
	}

	@Override
	public ProductDetail update(ProductDetail productDetail, Long id) {
		return detailRepository.save(productDetail);
	}

	@Override
	public void delete(Long id) {
		detailRepository.deleteById(id);
	}

	@Override
	public List<ProductDetail> findProductDetailsByProduct(Product product) {
		return detailRepository.findProductDetailsByProduct(product);
	}

	@Override
	public List<ProductDetail> findProductDetailsByCategory(int category, int idProduct) {
		//return detailRepository.findByProductAndId(category, idProduct);
		return null;
	}
}
