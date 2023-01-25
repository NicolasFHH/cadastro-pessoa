package br.com.api.controllers.responses;

import java.time.LocalDate;

import br.com.api.entidades.Pessoa;

public class PessoaResponse {
	
	private Long id;
	private String nome;
	private LocalDate dataDeNascimento;
	
	public PessoaResponse(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.dataDeNascimento = pessoa.getDataDeNascimento();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}
}