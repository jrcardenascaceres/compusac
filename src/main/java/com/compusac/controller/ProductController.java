package com.compusac.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compusac.models.service.ICategorysService;
import com.compusac.models.service.IProductService;

@Controller
@RequestMapping("/shop")
public class ProductController {

	@Autowired
	IProductService productoService;

	@Autowired
	ICategorysService categoryService;

	@GetMapping
	public String productos(Model model, HttpSession session) {
		model.addAttribute("categoria", categoryService.findAll());
		model.addAttribute("productos", productoService.findAll());

		return "shop";
	}

	@GetMapping(value = "/categoria/{id}")
	public String getById(@PathVariable("id") int id, Model model, HttpSession session) {
		model.addAttribute("status", false);
		try {
			model.addAttribute("categoria", categoryService.findAll());
			model.addAttribute("productos", productoService.findByIdCategory(id));
			model.addAttribute("status", true);
		} catch (NotFoundException nfe) {
			model.addAttribute("message", "No existe el producto en mención");
		}
		return "shop";
	}

	@GetMapping("/save-product/{id}")
	public String registrarProduct(@PathVariable("id") Long idProducto, HttpServletRequest request, Model model)
			throws NotFoundException {

		List<String> products = (List<String>) request.getSession().getAttribute("product_list");
		String idString = String.valueOf(idProducto);
		Boolean data = products.contains(idString);
		products.add(idString);
		request.getSession().setAttribute("product_list", products);
		request.getSession().setAttribute("cart",  products.size());
		model.addAttribute("categoria", categoryService.findAll());
		model.addAttribute("productos", productoService.findAll());

		return "shop";
	}

}
