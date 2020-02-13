package com.br.neo.tarefas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tarefas")
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "titulo", nullable = false, length = 50)
	@NotNull(message = "O título é obrigatório")
	@Length(min = 3, max = 50, message = "O título deve conter entre 3 e 50 caracteres")
	private String titulo;
	@Column(name = "descricao", nullable = true, length = 100)
	@Length(max = 50, message = "A descrição de conter até 100 caracteres")
	private String descricao;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataExpiracao",nullable = false)
//	FOI NECESSÁRIO TIRAR ESTA VALIDAÇÃO POIS ESTAVA ATRAPALHANDO QUANDO TENTAVA CONCLUIR A TAREFA
//	@FutureOrPresent(message = "A data de expiração não pode ser anterior a data atual.")
	private Date dataExpiracao;
	@Column(name = "situacao", nullable = false)
	private Boolean situacao = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

}
