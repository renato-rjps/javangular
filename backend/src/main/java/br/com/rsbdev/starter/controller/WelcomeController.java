package br.com.rsbdev.starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController extends AbstractRestController{
	
	@GetMapping
	public Welcome welcome() {
		
		return new Welcome("Welcome to RSBdev Starter");
	}
	
	class Welcome {
		private String text;
		
		public Welcome() {
		}
		
		public Welcome(final String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
		
	}

}
