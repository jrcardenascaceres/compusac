package com.compusac.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compusac.models.entity.Person;
import com.compusac.models.entity.Usuario;
import com.compusac.models.service.IPersonService;
import com.compusac.models.service.IUserService;

@Controller
@RequestMapping ("/usuario")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IPersonService personService;
	
	//BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
	
	@GetMapping("/registro")
	public String create() {
		return "register";
	}
	
	@PostMapping ("/save")
	public String save(Person person , Usuario user) {
		
		Long idPersona =  personService.guardar(person).getId();
		
		user.setPerson(idPersona);
		
		user.setUserName(person.getEmail());
		//user.setUserPass(passEncode.encode(user.getUserPass()));
		userService.guardar(user);
		
		return "redirect:/index";
		
	}
	
	@GetMapping("/login")
	public String login () {
		
		return "login";
	}
	
	@GetMapping("/acceder")
	public String acceder(Usuario usuario , HttpSession session) {
		
		Optional<Usuario> user = userService.findByUserName(usuario.getUserName());
		
		if (user.isPresent()) {
			session.setAttribute("idusuario", user.get().getId());
			session.setAttribute("name", user.get().getUserName());
		}else {
			session.removeAttribute("idusuario");
			session.removeAttribute("name");
		}
		
		return "redirect:/index";
	}
}
