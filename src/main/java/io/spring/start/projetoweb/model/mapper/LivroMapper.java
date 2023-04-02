package io.spring.start.projetoweb.model.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import io.spring.start.projetoweb.model.dto.LivroDTO;
import io.spring.start.projetoweb.model.entity.LivroEntity;

@Component
public class LivroMapper {
	
	private CategoriaMapper categoriaMapper = new CategoriaMapper();
	private EditoraMapper editoraMapper = new EditoraMapper();

	public LivroDTO update(LivroEntity livro) {
		
		LivroDTO livroDTO = new LivroDTO();
		livroDTO.setId(livro.getId());
		livroDTO.setNome(livro.getNome());
		livroDTO.setDescricao(livro.getDescricao());
		livroDTO.setCategoria(categoriaMapper.update(livro.getCategoria()));
		livroDTO.setEditora(editoraMapper.update(livro.getEditora()));
		return livroDTO;
	}
	
	public LivroEntity update(LivroDTO livro) {
		LivroEntity livroEntity = new LivroEntity();
		livroEntity.setId(livro.getId());
		livroEntity.setNome(livro.getNome());
		livroEntity.setDescricao(livro.getDescricao());
		livroEntity.setCategoria(categoriaMapper.update(livro.getCategoria()));
		livroEntity.setEditora(livroEntity.getEditora());
		return livroEntity;
	}
	
	public List<LivroEntity> updateListEntity(List<LivroDTO> listaDTO){
		return listaDTO.stream()
			.map(livroDTO ->
				this.update(livroDTO))
			.toList();
	}
	
	public List<LivroDTO> updateListDTO(List<LivroEntity> listaEntity){
		return listaEntity.stream()
			.map(livroEntity ->
				this.update(livroEntity))
			.toList();
	}
}
