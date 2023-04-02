package io.spring.start.projetoweb.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.spring.start.projetoweb.model.dto.UsuarioDTO;
import io.spring.start.projetoweb.model.dto.security.UsuarioComSenhaDTO;
import io.spring.start.projetoweb.model.entity.UsuarioEntity;

@Component
public class UsuarioMapper {
	
	@Autowired
	private PasswordEncoder encoder;

	public UsuarioEntity update(UsuarioComSenhaDTO usuarioDTO) {
		
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setCpf(usuarioDTO.getCpf());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setNome(usuarioDTO.getNome());
		usuario.setPassword(encoder.encode(usuarioDTO.getPassword()));
		usuario.setUsername(usuarioDTO.getUsername());
		
		return usuario;
	}
	
	public UsuarioDTO update(UsuarioEntity usuarioEntity) {
		
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setCpf(usuarioEntity.getCpf());
		usuario.setEmail(usuarioEntity.getEmail());
		usuario.setNome(usuarioEntity.getNome());
		usuario.setId(usuarioEntity.getId());
		usuario.setUsername(usuarioEntity.getUsername());
		
		return usuario;
	}
}
