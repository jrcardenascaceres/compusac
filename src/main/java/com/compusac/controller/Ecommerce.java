package com.compusac.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class Ecommerce {

	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("nombre", "Juan");
		return "index";
	}
}
