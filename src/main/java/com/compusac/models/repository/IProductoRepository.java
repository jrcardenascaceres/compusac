package com.compusac.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.compusac.models.entity.Producto;

public interface IProductoRepository extends CrudRepository<Producto, Long> {

}
