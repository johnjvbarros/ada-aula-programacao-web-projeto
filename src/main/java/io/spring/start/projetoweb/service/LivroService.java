package io.spring.start.projetoweb.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import io.spring.start.projetoweb.model.entity.EditoraEntity;
import io.spring.start.projetoweb.repository.CategoriaRepository;
import io.spring.start.projetoweb.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.start.projetoweb.model.dto.LivroDTO;
import io.spring.start.projetoweb.model.entity.CategoriaEntity;
import io.spring.start.projetoweb.model.entity.LivroEntity;
import io.spring.start.projetoweb.model.mapper.LivroMapper;
import io.spring.start.projetoweb.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;
	@Autowired
	private EditoraRepository editoraRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroMapper mapper;

	public LivroDTO pegarPorId(Integer id) {
		
		Optional<LivroEntity> produtoEntityOp =
					repository.findById(id);
		
		if(produtoEntityOp.isPresent()) {
			LivroEntity livroEntity = produtoEntityOp.get();
			return mapper.update(livroEntity);
		}
		
		throw new EntityNotFoundException("Produto não encontrado");
	}

	public LivroDTO criar(LivroDTO livroDTO) {
		
		LivroEntity categoria = mapper.update(livroDTO);
		
		System.out.println(categoria);
		
		categoria = repository.save(categoria);
		
		return mapper.update(categoria);
	}

	public LivroDTO editar(LivroDTO livroDTO, Integer id) {
		
		if(repository.existsById(id)) {
			
			LivroEntity livroEntity = mapper.update(livroDTO);
			livroEntity.setId(id);
			livroEntity = repository.save(livroEntity);
			
			return mapper.update(livroEntity);
		}
		
		throw new EntityNotFoundException("Categoria não encontrada");
	}

	public void deletar(Integer id){
		Optional<LivroEntity> produtoEntityOp =
				repository.findById(id);
	
		if(produtoEntityOp.isPresent()) {
			LivroEntity livroEntity = produtoEntityOp.get();
			repository.delete(livroEntity);
			return;
		}
	
		throw new EntityNotFoundException("Categoria não encontrada");
	}

	public List<LivroDTO> listar() {
		List<LivroEntity> listaEntities =  repository.findAll();
		return mapper.updateListDTO(listaEntities);
	}
	
	public List<LivroDTO> listarPorCategoria(Integer idCategoria) {
		CategoriaEntity categoria = new CategoriaEntity();
		categoria.setId(idCategoria);
		List<LivroEntity> listaEntities =  repository.findByCategoria(categoria);
		return mapper.updateListDTO(listaEntities);
	}

	public List<LivroDTO> listarPorEditora(Integer idCategoria) {
		EditoraEntity editora = new EditoraEntity();
		editora.setId(idCategoria);
		List<LivroEntity> listaEntities =  repository.findByEditora(editora);
		return mapper.updateListDTO(listaEntities);
	}

	public List<LivroDTO> buscarPorNomeOuIsbn(String nome, String isbn) {
		List<LivroEntity> livros = repository.findByNomeOuIsbn(nome, isbn);
		return mapper.updateListDTO(livros);
	}
	public List<LivroDTO> filtrar(String nome, String isbn) {
		List<LivroEntity> listaEntities =  repository.findByNomeOuIsbn(nome, isbn);
		return mapper.updateListDTO(listaEntities);
	}

}
