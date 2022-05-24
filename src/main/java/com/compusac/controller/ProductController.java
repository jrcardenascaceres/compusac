package com.compusac.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.compusac.models.entity.Categorys;
import com.compusac.models.entity.Product;
import com.compusac.models.service.ICategorysService;
import com.compusac.models.service.IProductService;

@Controller
//@RequestMapping("/productos")
public class ProductController {

	@Autowired
	IProductService productoService;

	@Autowired
	ICategorysService categoryService;

	@GetMapping(value = { "/shop" })
	public String productos(Model model, HttpSession session) {
		model.addAttribute("categoria", categoryService.findAll());
		model.addAttribute("productos", productoService.findAll());
		
		List<String> products = (List<String>) session.getAttribute("product_list");
		if (products != null) {
			model.addAttribute("cart", products.size());
		}
		
		return "shop";
	}
	
	@GetMapping(value = "shop/categoria/{id}")
	public String getById(@PathVariable("id") int id, Model model, HttpSession session) {
		model.addAttribute("status", false);
		try {
			model.addAttribute("categoria", categoryService.findAll());
			model.addAttribute("productos", productoService.findByIdCategory(id));
			model.addAttribute("status", true);
			List<String> products = (List<String>) session.getAttribute("product_list");
			if (products != null) {
				model.addAttribute("cart", products.size());
			}
		} catch (NotFoundException nfe) {
			model.addAttribute("message", "No existe el producto en mención");
		}
		return "shop";
	}

	@GetMapping("/shop/save-product/{id}")
	public String registrarProduct(@PathVariable("id") int id, HttpServletRequest request, Model model) {
		
        List<String> products = (List<String>) request.getSession().getAttribute("product_list");
        
        if (products == null) {
        	products = new ArrayList<>();
            request.getSession().setAttribute("product_list", products);
        } 
        String idString = String.valueOf(id);
        Boolean data = products.contains(idString);
        
        
        //if (data) {
        	
             products.add(idString);
             request.getSession().setAttribute("product_list", products);
             model.addAttribute("cart", products.size()); 
        //}
        model.addAttribute("categoria", categoryService.findAll());
		model.addAttribute("productos", productoService.findAll());
        
		return "shop";
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
