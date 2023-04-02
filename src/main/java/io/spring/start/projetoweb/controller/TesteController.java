package io.spring.start.projetoweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

	// GET - Listagem/Item
	
	@GetMapping("/{id}/{idDois}")
	public String testeGet(
			@RequestParam(name="nome",defaultValue="") String nome,
			@RequestParam(name="idade",required=false) String idade,
			@PathVariable("id") String id,
			@PathVariable("idDois") String idDois
			) {
		try {
			return "ID: "+id+"-"+idDois+", Eu sou "+nome+" e tenho "+Integer.parseInt(idade)+" anos";
		}catch(Exception ex) {
			return "Dados Inválidos";
		}
	}
}
