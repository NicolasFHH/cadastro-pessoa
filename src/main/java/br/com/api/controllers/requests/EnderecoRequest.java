package br.com.api.controllers.requests;

import br.com.api.entidades.Endereco;
import br.com.api.entidades.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EnderecoRequest {
	
	@NotBlank
	private String logradouro;
	@NotBlank
	private String cep;
	@NotBlank
	private Integer numero;
	private String cidade;
	@NotNull
	private Long idPessoa;
	
	public EnderecoRequest(@NotBlank String logradouro, @NotBlank String cep, @NotBlank Integer numero, String cidade,
			@NotNull Long idPessoa) {
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.idPessoa = idPessoa;
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

	public Long getIdPessoa() {
		return idPessoa;
	}

	public Endereco converter(Pessoa pessoa) {
		return new Endereco(logradouro, cep, numero, cidade, pessoa);
	}
}