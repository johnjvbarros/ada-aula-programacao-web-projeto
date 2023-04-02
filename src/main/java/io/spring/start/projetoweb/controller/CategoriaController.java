package io.spring.start.projetoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.start.projetoweb.model.dto.CategoriaDTO;
import io.spring.start.projetoweb.model.dto.MensagemDTO;
import io.spring.start.projetoweb.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/categorias")
@Slf4j
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<Object> listar(){
		
		try {
			return ResponseEntity.ok(categoriaService.listar());
			
		}catch(Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new MensagemDTO(ex.getMessage()));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> pegarUm(@PathVariable("id") Integer id){
		
		try {
			
			return ResponseEntity.ok(categoriaService.pegarPorId(id));
			
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
	
	@PostMapping
	public ResponseEntity<Object> criar(@RequestBody @Valid CategoriaDTO categoriaDTO) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(categoriaService.criar(categoriaDTO));
			
		}catch(Exception ex) {
			
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new MensagemDTO(ex.getMessage()));
		}	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> editar(
			@RequestBody @Valid CategoriaDTO categoriaDTO,
			@PathVariable("id") Integer id) {
		
		try {
			return ResponseEntity.ok(
					categoriaService.editar(categoriaDTO, id));
			
		}catch(EntityNotFoundException ex) {
			
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new MensagemDTO(ex.getMessage()));
			
		}catch(Exception ex) {
			
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new MensagemDTO(ex.getMessage()));
		}	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletar(
			@PathVariable("id") Integer id) {
		
		try {
			categoriaService.deletar(id);
			return ResponseEntity
					.ok(new MensagemDTO("Categoria com id "+id+" removido com sucesso!"));
		
		}catch(EntityNotFoundException ex) {
			
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new MensagemDTO(ex.getMessage()));
			
		}catch(Exception ex) {
			
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new MensagemDTO(ex.getMessage()));
		}
	}
}
