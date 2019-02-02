package br.com.rsbdev.starter.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rsbdev.starter.model.Pessoa;
import br.com.rsbdev.starter.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController  extends AbstractRestController {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@GetMapping
	public Collection<Pessoa> pessoas() {
		return pessoaRepository.findAll();
	}

}
