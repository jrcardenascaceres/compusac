package com.compusac.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.compusac.models.entity.Product;
import com.compusac.models.entity.ProductDetail;
import com.compusac.models.service.IProductDetailService;
import com.compusac.models.service.IProductService;

@Controller
public class ProductDetailController {

	@Autowired
	IProductDetailService detailService;

	@Autowired
	IProductService productoService;

	@GetMapping(value = "/shop-details/{productId}")
	public String getContactById(Model model, @PathVariable String productId) throws NotFoundException {
		model.addAttribute("status", false);
		try {
			Product product = productoService.findById(Long.parseLong(productId));
			model.addAttribute("producto", product);
			model.addAttribute("productosrel",
					detailService.findProductDetailsByCategory(product.getCategory(), Integer.parseInt(productId)));

			List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
			productDetails.addAll(detailService.findProductDetailsByProduct(Integer.parseInt(productId)));

			model.addAttribute("productDetails", productDetails);
			model.addAttribute("status", true);
		} catch (NotFoundException nfe) {
			model.addAttribute("message", "No existe el producto en mención");
		}

		return "shop-details";
	}
}
