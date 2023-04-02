package io.spring.start.projetoweb.controller;

import java.math.BigDecimal;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.spring.start.projetoweb.model.dto.MensagemDTO;
import io.spring.start.projetoweb.model.dto.LivroDTO;
import io.spring.start.projetoweb.service.LivroService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/livros")
@Slf4j
public class LivroController {

	@Autowired
	private LivroService livroService;
	
	@GetMapping("/categoria/{idCategoria}")
	public ResponseEntity<Object> pegarPorCategoria(@PathVariable Integer idCategoria){
		try {
			return ResponseEntity.ok(livroService.listarPorCategoria(idCategoria));
			
		}catch(Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new MensagemDTO(ex.getMessage()));
		}
		
	}

	@GetMapping("/editora/{id}")
	public ResponseEntity<Object> buscarPorEditora(@PathVariable Integer id) {
		try {
		return ResponseEntity.ok(livroService.listarPorEditora(id));
		}catch(Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new MensagemDTO(ex.getMessage()));
		}
	}

	@GetMapping("/buscar")
	public ResponseEntity<Object> buscarPorNomeOuIsbn(@RequestParam(required = false) String nome, @RequestParam(required = false) String isbn) {
		try {
			return ResponseEntity.ok(livroService.buscarPorNomeOuIsbn(nome, isbn));
		}catch(Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new MensagemDTO(ex.getMessage()));
		}
	}
	
	@GetMapping("/filter")
	public ResponseEntity<Object> pegarPorNomeIsbn(
			@RequestParam(name="nome",defaultValue = "") String nome,
			@RequestParam(name="isbn",defaultValue = "") String isbn
			){
		try {
			return ResponseEntity.ok(livroService.filtrar(nome, isbn));
			
		}catch(Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new MensagemDTO(ex.getMessage()));
		}
		
	}
	
	@GetMapping
	public ResponseEntity<Object> listar(){
		
		try {
			return ResponseEntity.ok(livroService.listar());
			
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
			
			return ResponseEntity.ok(livroService.pegarPorId(id));
			
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
	public ResponseEntity<Object> criar(@RequestBody @Valid LivroDTO livroDTO) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(livroService.criar(livroDTO));
			
		}catch(Exception ex) {
			
			log.error(ex.getMessage());
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new MensagemDTO(ex.getMessage()));
		}	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> editar(
			@RequestBody @Valid LivroDTO livroDTO,
			@PathVariable("id") Integer id) {
		
		try {
			return ResponseEntity.ok(
					livroService.editar(livroDTO, id));
			
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
			livroService.deletar(id);
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
