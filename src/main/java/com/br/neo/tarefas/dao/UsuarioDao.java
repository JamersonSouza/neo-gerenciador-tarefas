package com.br.neo.tarefas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.neo.tarefas.model.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
}
