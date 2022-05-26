package com.compusac.models.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.compusac.models.entity.Usuario;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IUserService userService;
	
//	@Autowired
//	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	HttpSession session;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optionalUser = userService.findByUserName(username);
		
		if (optionalUser.isPresent()) {
			session.setAttribute("idusuario", optionalUser.get().getId());
			
			Usuario usuario = optionalUser.get();
			return User.builder().username(usuario.getUserName()).password(usuario.getUserPass()).build(); //bCrypt.encode
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
				
	}
	
}
