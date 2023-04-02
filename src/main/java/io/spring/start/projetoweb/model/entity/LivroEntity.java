package io.spring.start.projetoweb.model.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="produto")
public class LivroEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="categoria", nullable=false)
	private CategoriaEntity categoria;

	@OneToOne
	@JoinColumn(name="editora", nullable=false)
	private EditoraEntity editora;
	@Column(name="nome",nullable=false,unique=true)
	private String nome;
	
	@Column(name="isbn",nullable=false)
	private String isbn;
	
	@Column(name="descricao")
	private String descricao;
}
