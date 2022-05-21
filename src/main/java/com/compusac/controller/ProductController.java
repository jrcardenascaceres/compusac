package com.compusac.controller;

import java.util.List;

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

import com.compusac.models.entity.Categorys;
import com.compusac.models.entity.Product;
import com.compusac.models.service.ICategorysService;
import com.compusac.models.service.IProductService;

@Controller
@RequestMapping("/productos")
public class ProductController {

	@Autowired
	IProductService productoService;
	
	@Autowired
	ICategorysService categoryService;

	/*@GetMapping("")
	public String getAll(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "productos";
	}
*/
	
	@GetMapping("/buscar/{name}")
	public String getByIdProduct(@PathVariable("name") Long id, Model model) {
		model.addAttribute("categoria", categoryService.findAll());
		//model.addAttribute("producto", productoService.findProductName(name));

		return "shop-findID";
	}
	
	@GetMapping("/detalle/{id}")
	public String getById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("status", false);
		try {
			model.addAttribute("categoria", categoryService.findAll());
			model.addAttribute("productos", productoService.findById(id));
			model.addAttribute("status", true);
		} catch (NotFoundException nfe) {
			model.addAttribute("message", "No existe el producto en mención");
		}

		return "shop-findID";
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
	
	
	@GetMapping("")
	public String productos(Model model) {
				
		model.addAttribute("categoria", categoryService.findAll());
		model.addAttribute("productos", productoService.findAll());
		return "shop";
	}
	
}
