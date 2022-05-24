package com.compusac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compusac.models.entity.Person;
import com.compusac.models.entity.User;
import com.compusac.models.service.IPersonService;
import com.compusac.models.service.IUserService;

@Controller
@RequestMapping ("/usuario")
public class UserController {

	@Autowired
	private IUserService userService;
	private IPersonService personService;
	
	@GetMapping("/registro")
	public String create() {
		return "register";
	}
	
	@PostMapping ("/save")
	public String save(Person person , User user) {
		
		Long idPersona =  personService.guardar(person).getId();
		
		user.setPerson(idPersona);
		user.setUserName(person.getEmail());
		userService.guardar(user);
		
		return "redirect:/index";
		
	}
	
}
