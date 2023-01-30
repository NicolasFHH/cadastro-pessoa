package br.com.api.controllers.responses;

import br.com.api.entidades.Endereco;

public class EnderecoListResponse {
	
	private Long id;
	private String logradouro;
	private String cep;
	private Integer numero;
	private String cidade;
	private boolean enderecoPrincipal;
	
	public EnderecoListResponse(Endereco endereco) {
		this.id = endereco.getId();
		this.logradouro = endereco.getLogradouro();
		this.cep = endereco.getCEP();
		this.numero = endereco.getNumero();
		this.cidade = endereco.getCidade();
		this.enderecoPrincipal = endereco.isEnderecoPrincipal();
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

	public boolean isEnderecoPrincipal() {
		return enderecoPrincipal;
	}
}