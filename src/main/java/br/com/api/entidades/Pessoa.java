package br.com.api.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	@JsonFormat
	(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate dataDeNascimento;
	@OneToMany(mappedBy = "pessoa")
	private List<Endereco> enderecos = new ArrayList<>();
	
	@Deprecated
	public Pessoa() {
	}
	
	public Pessoa(String nome, LocalDate dataDeNascimento) {
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public boolean encontraEndereco(long idEndereco) {
		for (Endereco endereco : enderecos) {
			if (endereco.getId() == idEndereco) {
				return true;
			}
		}
		return false;
	}
	
	public void marcaEnderecoPrincipal(long idEndereco) {
		for (Endereco endereco : enderecos) {
			if (endereco.getId() == idEndereco) {
				endereco.setEnderecoPrincipal(true);
			} else {
				endereco.setEnderecoPrincipal(false);
			}
		}
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}
}