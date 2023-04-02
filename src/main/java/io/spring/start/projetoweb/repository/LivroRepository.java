package io.spring.start.projetoweb.repository;

import java.math.BigDecimal;
import java.util.List;

import io.spring.start.projetoweb.model.entity.EditoraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.spring.start.projetoweb.model.entity.CategoriaEntity;
import io.spring.start.projetoweb.model.entity.LivroEntity;

@Repository
public interface LivroRepository
	extends JpaRepository<LivroEntity,Integer>{
	
	//SELECT * FROM produto WHERE nome = 'Arro' OR (preco BETWEEN 2 AND 10); 

	@Query("SELECT l FROM LivroEntity l "
		+ "WHERE UPPER(l.nome) LIKE CONCAT('%',UPPER(:nome),'%') "
		+ "OR UPPER(l.isbn) LIKE CONCAT('%',UPPER(:isbn),'%') ")
	List<LivroEntity> findByNomeOuIsbn(@Param("nome") String nome, String isbn);

	List<LivroEntity> findByCategoria(CategoriaEntity categoria);

	List<LivroEntity> findByEditora(EditoraEntity editora);

}
