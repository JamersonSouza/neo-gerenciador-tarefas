package com.br.neo.tarefas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.neo.tarefas.model.Tarefa;

public interface TarefaDao extends JpaRepository<Tarefa, Long> {

	@Query("select t from Tarefa t Where t.usuario.email = :emailUsuario")
	List<Tarefa> carregarTarefasPorUsuario(@Param("emailUsuario") String email);
	
}
