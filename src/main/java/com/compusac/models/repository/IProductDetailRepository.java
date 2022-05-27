package com.compusac.models.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.compusac.models.entity.Product;
import com.compusac.models.entity.ProductDetail;

public interface IProductDetailRepository extends CrudRepository<ProductDetail, Long> {
	public List<ProductDetail> findProductDetailsByProduct(Product product);

	/*@Query(value = "SELECT pd FROM product_details pd INNER JOIN products p ON pd.product = p.id WHERE p.category = :category and p.id <> :idproduct")
	public List<ProductDetail> findByProductAndId(@Param("category") int category, @Param("idproduct") int idproduct);*/
}
