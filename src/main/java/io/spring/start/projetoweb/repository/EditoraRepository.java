package io.spring.start.projetoweb.repository;

import io.spring.start.projetoweb.model.entity.EditoraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository
        extends JpaRepository<EditoraEntity,Integer> {

}
