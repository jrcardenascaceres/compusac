package com.compusac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compusac.models.entity.Producto;
import com.compusac.models.service.IProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	IProductoService productoService;

	@GetMapping("")
	public String getAll(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "productos";
	}

	@GetMapping("/{id}")
	public String getById(@PathVariable Long id, Model model) {
		Producto producto = productoService.findById(id);
		model.addAttribute("producto", producto);
		return "productos";
	}

	@GetMapping("/registrar")
	public String registrar() {
		return "registrar-producto";
	}

	@PostMapping("/addProducto")
	public String addProducto(@Validated Producto producto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "registrar-producto";
		}

		productoService.create(producto);
		return "redirect:/productos";
	}
}
