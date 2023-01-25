package br.com.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entidades.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
}