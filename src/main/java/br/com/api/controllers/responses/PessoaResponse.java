package br.com.api.controllers.responses;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.api.entidades.Pessoa;

public class PessoaResponse {
	
	private Long id;
	private String nome;
	private LocalDate dataDeNascimento;
	private List<EnderecoListResponse> endereco = new ArrayList<>();
	
	public PessoaResponse(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.dataDeNascimento = pessoa.getDataDeNascimento();
		this.endereco = pessoa.getEnderecos().stream().map(EnderecoListResponse::new).collect(Collectors.toList());
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

	public List<EnderecoListResponse> getEndereco() {
		return endereco;
	}
}