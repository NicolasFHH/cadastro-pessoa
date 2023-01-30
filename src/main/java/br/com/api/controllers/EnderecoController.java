package br.com.api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.controllers.requests.EnderecoPrincipalRequest;
import br.com.api.controllers.requests.EnderecoRequest;
import br.com.api.controllers.responses.EnderecoListResponse;
import br.com.api.controllers.responses.EnderecoResponse;
import br.com.api.controllers.responses.PessoaResponse;
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
	public ResponseEntity<EnderecoListResponse> novoEndereco(@RequestBody @Valid EnderecoRequest enderecoRequest) {
		Optional<Pessoa> possivelPessoa = pessoaRepository.findById(enderecoRequest.getIdPessoa());
		if (possivelPessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Endereco endereco = enderecoRequest.converter(possivelPessoa.get());
		enderecoRepository.save(endereco);
		return ResponseEntity.ok().body(new EnderecoListResponse(endereco));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EnderecoResponse> getEnderecoDePessoa(@PathVariable long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if (pessoa.isPresent()) {
			return ResponseEntity.ok().body(new EnderecoResponse(pessoa.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PatchMapping
	public ResponseEntity<PessoaResponse> escolheEnderecoPrincipal(@RequestBody @Valid EnderecoPrincipalRequest request) {
		Optional<Pessoa> possivelPessoa = pessoaRepository.findById(request.getIdPessoa());
		if (possivelPessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Pessoa pessoa = possivelPessoa.get();
		Boolean encontraEndereco = pessoa.encontraEndereco(request.getIdEndereco());
		if (!encontraEndereco) {
			return ResponseEntity.notFound().build();
		}
		pessoa.marcaEnderecoPrincipal(request.getIdEndereco());
		pessoaRepository.save(pessoa);
		return ResponseEntity.ok().body(new PessoaResponse(pessoa));
	}
}