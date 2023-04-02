package io.spring.start.projetoweb.service;

import io.spring.start.projetoweb.model.dto.EditoraDTO;
import io.spring.start.projetoweb.model.entity.EditoraEntity;
import io.spring.start.projetoweb.model.mapper.EditoraMapper;
import io.spring.start.projetoweb.repository.EditoraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {
    @Autowired
    private EditoraRepository repository;
    @Autowired
    private EditoraMapper mapper;
    public EditoraDTO pegarPorId(Integer id){
        Optional<EditoraEntity> editoraEntityOp =
                repository.findById(id);

        if(editoraEntityOp.isPresent()) {
            EditoraEntity editoraEntity = editoraEntityOp.get();
            return mapper.update(editoraEntity);
        }

        throw new EntityNotFoundException("Editora não encontrada");
    }


    public EditoraDTO criar(EditoraDTO editoraDTO) {

        EditoraEntity editora = mapper.update(editoraDTO);

        editora = repository.save(editora);

        return mapper.update(editora);
    }

    public EditoraDTO editar(EditoraDTO editoraDTO, Integer id) {

        if(repository.existsById(id)) {

            EditoraEntity editoraEntity = mapper.update(editoraDTO);
            editoraEntity.setId(id);
            editoraEntity = repository.save(editoraEntity);

            return mapper.update(editoraEntity);
        }

        throw new EntityNotFoundException("Editora não encontrada");
    }

    public void deletar(Integer id){
        Optional<EditoraEntity> editoraEntityOp =
                repository.findById(id);

        if(editoraEntityOp.isPresent()) {
            EditoraEntity editoraEntity = editoraEntityOp.get();
            repository.delete(editoraEntity);
            return;
        }

        throw new EntityNotFoundException("Editora não encontrada");
    }

    public List<EditoraDTO> listar() {
        List<EditoraEntity> listaEntities =  repository.findAll();
        return mapper.updateListDTO(listaEntities);
    }
}
