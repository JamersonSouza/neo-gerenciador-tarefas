package com.br.neo.tarefas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.neo.tarefas.model.Usuario;
import com.br.neo.tarefas.service.ServicoUsuario;

@Controller
public class LoginController {

	@Autowired
	private ServicoUsuario servicoUsuario;
	
	@GetMapping
	public String login() {
		return "conta/login";
	}
	
	@GetMapping("/registration")
	public ModelAndView registration() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("conta/registrar");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@PostMapping("/registration")
	public ModelAndView registration2(@Valid Usuario usuario, BindingResult br) {
		ModelAndView mv = new ModelAndView();
		Usuario usr = servicoUsuario.encontrarPorEmail(usuario.getEmail());
		if(usr != null) {
			br.rejectValue("email", "", "usuario já cadastrado no sistema");
		}
		if(br.hasErrors()) {
			mv.setViewName("conta/registrar");
			mv.addObject("usuario", usuario);
		}else {
			servicoUsuario.salvarUsuario(usuario);
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
}
