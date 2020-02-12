package com.br.neo.tarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.neo.tarefas.dao.UsuarioDao;
import com.br.neo.tarefas.model.Usuario;

@Service
public class ServicoUsuario {
	
	@Autowired
	private UsuarioDao repositorioUsuario;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
		public Usuario encontrarPorEmail(String email) {
			
			return repositorioUsuario.findByEmail(email);
			
		}
		
		public void salvarUsuario(Usuario usuario) {
			
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			repositorioUsuario.save(usuario);
			
		}
	
}
