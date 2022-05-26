package com.compusac.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.compusac.models.entity.Person;
import com.compusac.models.entity.Usuario;
import com.compusac.models.service.IPersonService;
import com.compusac.models.service.IUserService;
import com.compusac.models.service.SendMailService;

@Controller
@RequestMapping ("/contact")
public class ContactController {

	@Autowired
	private SendMailService sendMailService;
	
	//BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
	
	@GetMapping("")
	public String index() {
		return "contact";
	}
	
	@PostMapping ("/sendMail")
	public String sendMail(@RequestParam("name") String name, @RequestParam("mail") String mail, @RequestParam("message") String message) {
		
		try {
			sendMailService.sendMail(mail, "compusac.peru@gmail.com", "Contacto del cliente:" + name, "Correo: mail\n\n" + message);	
			return "redirect:/contact";
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return "contact";
		}


		
	}
	

}
