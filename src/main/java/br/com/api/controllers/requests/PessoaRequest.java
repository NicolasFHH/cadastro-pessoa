package br.com.api.controllers.requests;

import java.time.LocalDate;

import br.com.api.entidades.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public class PessoaRequest {
	
	@NotBlank
	private String nome;
	@NotNull
	@Past
	private LocalDate dataDeNascimento;
	
	public PessoaRequest(@NotBlank String nome, @NotNull @Past LocalDate dataDeNascimento) {
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public Pessoa converter() {
		return new Pessoa(nome, dataDeNascimento);
	}
}
