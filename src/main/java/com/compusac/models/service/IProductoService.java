package com.compusac.models.service;

import java.util.List;

import com.compusac.models.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();

	public Producto findById(Long id);

	public Producto create(Producto producto);

	public Producto update(Producto producto, Long id);

	public void delete(Long id);
}
