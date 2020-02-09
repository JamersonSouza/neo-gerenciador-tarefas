package com.br.neo.tarefas.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.neo.tarefas.model.Tarefa;

public interface TarefaDao extends JpaRepository<Tarefa, Long> {

}
