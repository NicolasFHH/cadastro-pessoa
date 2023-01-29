package br.com.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entidades.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	List<Endereco> findByPessoaId(long id);
}