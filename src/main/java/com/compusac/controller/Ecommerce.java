package com.compusac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compusac.models.service.IOfferService;
import com.compusac.models.service.IProductDetailService;
//import com.compusac.models.service.IOfferService;
import com.compusac.models.service.IProductService;

@Controller
public class Ecommerce {

	@Autowired
	IProductService productoService;
	
	@Autowired
	IProductDetailService productoDetailService;

	@Autowired
	IOfferService offerService;

	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("productos", productoService.findAll());
		model.addAttribute("producto_detalles", productoDetailService.findAll());
		model.addAttribute("ofertas", offerService.findAll());
		return "index";
	}
}
