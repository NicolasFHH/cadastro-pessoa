package br.com.api.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String logradouro;
	@Column(nullable = false)
	private String CEP;
	@Column(nullable = false)
	private Integer numero;
	private String cidade;
	private boolean enderecoPrincipal;
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@Deprecated
	public Endereco() {
	}

	public Endereco(String logradouro, String cEP, Integer numero, String cidade, boolean enderecoPrincipal,
			Pessoa pessoa) {
		this.logradouro = logradouro;
		CEP = cEP;
		this.numero = numero;
		this.cidade = cidade;
		this.enderecoPrincipal = enderecoPrincipal;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getCEP() {
		return CEP;
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

	public Pessoa getPessoa() {
		return pessoa;
	}
}