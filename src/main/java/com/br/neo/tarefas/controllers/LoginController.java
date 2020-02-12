package com.br.neo.tarefas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping
	public String login() {
		return "conta/login";
	}
	
}
