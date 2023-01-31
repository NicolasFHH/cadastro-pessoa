package br.com.api;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.api.entidades.Pessoa;
import br.com.api.repositorios.PessoaRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
public class PessoaControllerTest {
	
	private String uri = "/api/pessoas";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@BeforeEach
	void setup() {
		Pessoa pessoa = new Pessoa("João Roberto", LocalDate.of(1995, 05, 25));
		pessoaRepository.save(pessoa);
	}
	
	@Test
	void naoDeveCriarPessoaSemNomeOuEmBranco() throws Exception {
		String pessoaDTO = "{\"nome\": \"\", \"dataDeNascimento\": \"1995-05-25\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(pessoaDTO).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void naoDeveCriarPessoaSemDataDeNascimento() throws Exception {
		String pessoaDTO = "{\"nome\": \"João Roberto\", \"dataDeNascimento\": \"\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(pessoaDTO).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void naoDeveCriarPessoaComDataDeNascimentoNoFuturo() throws Exception {
		String pessoaDTO = "{\"nome\": \"João Roberto\", \"dataDeNascimento\": \"2030-10-05\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(pessoaDTO).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void deveCriarPessoaQuandoEstaTudoCerto() throws Exception {
		String pessoaDTO = "{\"nome\": \"João Roberto\", \"dataDeNascimento\": \"1995-05-25\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(pessoaDTO).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	void deveRetornarListaDePessoasComMetodoFindAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void deveRetornarUmaPessoaComMetodoFindById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(uri + "/1"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void nãoDeveRetornarUmaPessoaComIdInexistenteComMetodoFindById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(uri + "/35"))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void deveAtualizarNomeOuDataDeNascimentoDeUmaPessoa() throws Exception {
		String pessoaDTO = "{\"nome\": \"João Roberto Silva\", \"dataDeNascimento\": \"1995-05-25\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.put(uri + "/1" + "/atualiza-pessoa").content(pessoaDTO).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void nãoDeveAtualizarNomeOuDataDeNascimentoDeUmaPessoaComIdInexistente() throws Exception {
		String pessoaDTO = "{\"nome\": \"João Roberto Silva\", \"dataDeNascimento\": \"1995-05-25\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.put(uri + "/35" + "/atualiza-pessoa").content(pessoaDTO).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}