package com.compusac.models.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.compusac.models.entity.Sale;

public interface ISalesRepository extends JpaRepository<Sale, Long>{
	

	@Query(value = "SELECT * FROM sales WHERE user = :user")
	List<Sale> findByUser(@Param("user") Integer user); 
}
