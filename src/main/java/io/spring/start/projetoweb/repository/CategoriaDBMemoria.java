package io.spring.start.projetoweb.repository;

import java.util.ArrayList;
import java.util.List;

import io.spring.start.projetoweb.model.dto.CategoriaDTO;

public class CategoriaDBMemoria {
  private List<CategoriaDTO> lista = new ArrayList<>();
  private Integer contador = 1;


  public CategoriaDTO pegarUm(Integer id) {
    Integer indice = null;
    for(Integer i = 0;i<lista.size();i++) {
      if(lista.get(i).getId().equals(id)) {
        indice = i;
        break;
      }
    }
    return lista.get(indice);
  }

  public CategoriaDTO criar(CategoriaDTO categoriaDTO) {
	  categoriaDTO.setId(contador);
    lista.add(categoriaDTO);
    contador++;

    return categoriaDTO;
  }

  public CategoriaDTO editar(CategoriaDTO categoriaDTO, Integer id) {
	categoriaDTO.setId(id);

    Integer indice = null;
    for(Integer i = 0;i<lista.size();i++) {
      if(lista.get(i).getId().equals(id)) {
        indice = i;
        break;
      }
    }

    lista.set(indice, categoriaDTO);

    return categoriaDTO;
  }

  public CategoriaDTO editar(CategoriaDTO categoriaDTO) {
    int primitivo = 1;
    int primitivo2 = 1;
    for (Integer i = 0; i< lista.size(); i++) {
      if (lista.get(i).getId().equals(categoriaDTO.getId())) {
        lista.set(i, categoriaDTO);
      } else {
        throw new RuntimeException("CategoriaDTO não existente");
      }
    }
    return categoriaDTO;
  }

  public void delete(Integer id) {
    int indice = -1;
    for(Integer i = 0;i<lista.size();i++) {
      if(lista.get(i).getId().equals(id)) {
        indice = i;
        break;
      }
    }

    lista.remove(indice);
  }

  public List<CategoriaDTO> listAll() {
    return lista;
  }
}
