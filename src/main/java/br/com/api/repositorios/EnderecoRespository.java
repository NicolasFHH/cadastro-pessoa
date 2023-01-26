package br.com.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entidades.Endereco;

public interface EnderecoRespository extends JpaRepository<Endereco, Long> {

}