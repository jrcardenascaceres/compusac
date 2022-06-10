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
import org.springframework.web.bind.annotation.RequestParam;

import com.compusac.models.entity.Sale;
import com.compusac.models.service.IProductService;
import com.compusac.models.service.ISalesService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
	ISalesService salesService;

	@Autowired
	IProductService productService;

	@GetMapping("/findID/{id}")
	public String getById(@PathVariable("id") Long id, Model model) {
		// model.addAttribute("status", false);
		try {
			model.addAttribute("productos", productService.findAll());
			model.addAttribute("ventas", salesService.findById(id));
			// model.addAttribute("status", true);
		} catch (NotFoundException nfe) {
			model.addAttribute("message", "No existe el producto en mención");
		}

		return "shopfindID";
	}

	@GetMapping("/registrar")
	public String registrar() {
		return "registrar-venta";
	}

	@PostMapping("/addVenta")
	public String addVenta(@Validated Sale sale, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "registrar-producto";
		}

		salesService.create(sale);
		return "redirect:/sales";
	}

	@GetMapping("")
	public String sales(Model model) {

		model.addAttribute("ventas", salesService.findAll());
		model.addAttribute("totalVenta", salesService.total(1));

		return "checkout";
	}

	@GetMapping("/delete")
	public String eliminar(@RequestParam Long id) {
		salesService.delete(id);
		// model.addAttribute("status", true);

		return "redirect:/shopping-cart";
	}

}
