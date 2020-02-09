package com.br.neo.tarefas.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.neo.tarefas.dao.TarefaDao;
import com.br.neo.tarefas.model.Tarefa;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

	@Autowired
	private TarefaDao repositorioTarefa;

	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tarefas/listar");
		mv.addObject("tarefas", repositorioTarefa.findAll());
		return mv;

	}
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tarefas/inserir");
		mv.addObject("tarefa", new Tarefa());
		return mv;
	}
	
	@PostMapping("/inserir")
	public ModelAndView inserir(@Valid Tarefa tarefa, BindingResult br) {
		ModelAndView mv = new ModelAndView();
		if(tarefa.getDataExpiracao() == null) {
			br.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida", ""
					+ "A data de Expiração esta Inválida ou Vazia.");
		}
		if(br.hasErrors()) {
			mv.setViewName("tarefas/inserir");
			mv.addObject(tarefa);
		}else {
		mv.setViewName("redirect:/tarefas/listar");
		repositorioTarefa.save(tarefa);
		}
		
		return mv;
}

}
