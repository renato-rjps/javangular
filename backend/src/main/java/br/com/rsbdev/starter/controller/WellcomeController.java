package br.com.rsbdev.starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WellcomeController extends AbstractRestController{
	
	@GetMapping
	public String welcome() {
		this.LOGGER.info("Welcome to RSBdev Starter");
		return "Welcome to RSBdev Starter";
	}

}
