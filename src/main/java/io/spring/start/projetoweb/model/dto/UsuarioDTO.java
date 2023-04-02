package io.spring.start.projetoweb.model.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
	
	private Integer id;
	private String email;
	private String nome;
	private String username;
	private String cpf;
}
