package br.com.api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.controllers.requests.EnderecoRequest;
import br.com.api.controllers.responses.EnderecoResponse;
import br.com.api.entidades.Endereco;
import br.com.api.entidades.Pessoa;
import br.com.api.repositorios.EnderecoRepository;
import br.com.api.repositorios.PessoaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<EnderecoResponse> novoEndereco(@RequestBody @Valid EnderecoRequest enderecoRequest) {
		Optional<Pessoa> possivelPessoa = pessoaRepository.findById(enderecoRequest.getIdPessoa());
		if (possivelPessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Endereco endereco = enderecoRequest.converter(possivelPessoa.get());
		enderecoRepository.save(endereco);
		return ResponseEntity.ok().body(new EnderecoResponse(endereco));
	}
}