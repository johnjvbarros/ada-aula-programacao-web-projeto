package io.spring.start.projetoweb.model.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LivroDTO {
	
	private Integer id;
	@Size(max=80,message="Tamanho do nome ácima do permitido")
	@NotBlank(message="Nome deve conter algum valor")
	private String nome;
	private CategoriaDTO categoria;
	private EditoraDTO editora;
	private String descricao;
}
