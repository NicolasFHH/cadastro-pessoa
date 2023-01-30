package br.com.api.controllers.responses;

import java.util.List;
import java.util.stream.Collectors;

import br.com.api.entidades.Pessoa;

public class EnderecoResponse {
	
	private String nomePessoa;
	private List<EnderecoListResponse> enderecoResponse;
	
	public EnderecoResponse(Pessoa pessoa) {
		this.nomePessoa = pessoa.getNome();
		this.enderecoResponse =
				pessoa.getEnderecos().stream().map(EnderecoListResponse::new).collect(Collectors.toList());
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public List<EnderecoListResponse> getEnderecoResponse() {
		return enderecoResponse;
	}
}