package br.com.api.controllers.requests;

import jakarta.validation.constraints.NotNull;

public class EnderecoPrincipalRequest {
	
	@NotNull
	private Long idPessoa;
	@NotNull
	private Long idEndereco;
	
	public EnderecoPrincipalRequest(Long idPessoa, Long idEndereco) {
		this.idPessoa = idPessoa;
		this.idEndereco = idEndereco;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}
}