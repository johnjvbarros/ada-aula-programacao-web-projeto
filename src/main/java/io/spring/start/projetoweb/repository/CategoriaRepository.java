package io.spring.start.projetoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.start.projetoweb.model.entity.CategoriaEntity;

@Repository
public interface CategoriaRepository 
	extends JpaRepository<CategoriaEntity,Integer>{

}
