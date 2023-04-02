package io.spring.start.projetoweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.spring.start.projetoweb.model.dto.UsuarioDTO;
import io.spring.start.projetoweb.model.dto.security.UsuarioComSenhaDTO;
import io.spring.start.projetoweb.model.dto.security.UsuarioLoginDTO;
import io.spring.start.projetoweb.model.entity.UsuarioEntity;
import io.spring.start.projetoweb.model.mapper.UsuarioMapper;
import io.spring.start.projetoweb.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioMapper mapper;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;

	public UsuarioDTO criar(UsuarioComSenhaDTO usuarioDTO) {
		UsuarioEntity usuario = mapper.update(usuarioDTO);
		usuario = repository.save(usuario);
		return mapper.update(usuario);
	}
	
	public void entrar(UsuarioLoginDTO usuario) {
		
		UsernamePasswordAuthenticationToken authentication = 
		UsernamePasswordAuthenticationToken.authenticated(usuario.getUsername(), usuario.getPassword(), null);
		
		Authentication auth = authManager.authenticate(authentication);
		
		if(auth.isAuthenticated()) {
			UsuarioEntity usuarioEntity = (UsuarioEntity) auth.getPrincipal();
			String token = jwtService.gerarToken(mapper.update(usuarioEntity));
			System.out.println(token);
		}
	}
}
