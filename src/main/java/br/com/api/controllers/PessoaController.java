package br.com.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.controllers.requests.PessoaRequest;
import br.com.api.controllers.responses.PessoaResponse;
import br.com.api.entidades.Pessoa;
import br.com.api.repositorios.PessoaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void novaPessoa(@RequestBody @Valid PessoaRequest pessoaRequest) {
		Pessoa pessoa = pessoaRequest.converter();
		pessoaRepository.save(pessoa);
	}
	
	@GetMapping
	public ResponseEntity<List<PessoaResponse>> findAll() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		List<PessoaResponse> pessoasResponse = pessoas.stream().map(PessoaResponse::new).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(pessoasResponse);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaResponse> getPessoaPorId(@PathVariable long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if (pessoa.isPresent()) {
			return ResponseEntity.ok().body(new PessoaResponse(pessoa.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}/atualiza-pessoa")
	public ResponseEntity<PessoaResponse> atualizaPessoa(@PathVariable Long id, @RequestBody @Valid PessoaRequest pessoaRequest) {
		Optional<Pessoa> possivelPessoa = pessoaRepository.findById(id);
		if (possivelPessoa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Pessoa pessoa = possivelPessoa.get();
		pessoa.setNome(pessoaRequest.getNome());
		pessoa.setDataDeNascimento(pessoaRequest.getDataDeNascimento());
		pessoaRepository.save(pessoa);
		return ResponseEntity.ok().body(new PessoaResponse(pessoa));
	}
	
}