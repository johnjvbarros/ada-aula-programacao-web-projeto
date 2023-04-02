package io.spring.start.projetoweb.model.dto.security;

import io.spring.start.projetoweb.model.dto.UsuarioDTO;
import lombok.Data;

@Data
public class UsuarioComSenhaDTO extends UsuarioDTO{
	
	private String password;
}
