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
	private String cep;
	@Column(nullable = false)
	private Integer numero;
	private String cidade;
	private boolean enderecoPrincipal = false;
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@Deprecated
	public Endereco() {
	}

	public Endereco(String logradouro, String cep, Integer numero, String cidade, Pessoa pessoa) {
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getCEP() {
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
	
	public void setEnderecoPrincipal(boolean enderecoPrincipal) {
		this.enderecoPrincipal = enderecoPrincipal;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
}