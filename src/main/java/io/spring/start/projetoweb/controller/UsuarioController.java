package io.spring.start.projetoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.start.projetoweb.model.dto.MensagemDTO;
import io.spring.start.projetoweb.model.dto.security.UsuarioComSenhaDTO;
import io.spring.start.projetoweb.model.dto.security.UsuarioLoginDTO;
import io.spring.start.projetoweb.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;

	@PostMapping
	public ResponseEntity<Object> criar(@RequestBody UsuarioComSenhaDTO usuario){
		try {
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(service.criar(usuario));
			
		}catch(Exception ex) {
			
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new MensagemDTO(ex.getMessage()));
		}
	}
	
	@PostMapping("/auth")
	public ResponseEntity<Object> entrar(@RequestBody UsuarioLoginDTO usuario){
		
		try {
			
			service.entrar(usuario);
			
			return ResponseEntity.ok().build();
			
		}catch(EntityNotFoundException ex) {
			
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.body(new MensagemDTO(ex.getMessage()));
			
		}catch(Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new MensagemDTO(ex.getMessage()));
		}
	}
}
