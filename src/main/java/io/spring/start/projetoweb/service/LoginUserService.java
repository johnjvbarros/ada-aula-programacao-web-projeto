package io.spring.start.projetoweb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.spring.start.projetoweb.model.entity.UsuarioEntity;
import io.spring.start.projetoweb.repository.UsuarioRepository;

@Service
public class LoginUserService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UsuarioEntity> usuarioOp = repository.findByUsername(username);
		
		if(usuarioOp.isPresent()) {
			return usuarioOp.get();
		}
		
		throw new UsernameNotFoundException("Usuário não encontrado");
	}

}
