package com.compusac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compusac.models.entity.Product;
import com.compusac.models.service.IProductService;

@Controller
@RequestMapping("/productos")
public class ProductController {

	@Autowired
	IProductService productoService;

	@GetMapping("")
	public String getAll(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "productos";
	}

	@GetMapping("/{id}")
	public String getById(@PathVariable Long id, Model model) throws NotFoundException {
		model.addAttribute("status", false);
		try {
			Product product = productoService.findById(id);
			
			model.addAttribute("producto", product);
			model.addAttribute("status", true);
		} catch (NotFoundException nfe) {
			model.addAttribute("message", "No existe el producto en mención");
		}

		return "productos";
	}

	@GetMapping("/registrar")
	public String registrar() {
		return "registrar-producto";
	}

	@PostMapping("/addProducto")
	public String addProducto(@Validated Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "registrar-producto";
		}

		productoService.create(product);
		return "redirect:/productos";
	}
}
