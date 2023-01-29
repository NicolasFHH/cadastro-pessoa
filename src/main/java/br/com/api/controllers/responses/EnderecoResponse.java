package br.com.api.controllers.responses;

import br.com.api.entidades.Endereco;

public class EnderecoResponse {
	
	private Long id;
	private String logradouro;
	private String cep;
	private Integer numero;
	private String cidade;
	
	public EnderecoResponse(Endereco endereco) {
		this.id = endereco.getId();
		this.logradouro = endereco.getLogradouro();
		this.cep = endereco.getCEP();
		this.numero = endereco.getNumero();
		this.cidade = endereco.getCidade();
	}

	public Long getId() {
		return id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getCep() {
		return cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getCidade() {
		return cidade;
	}
}