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

import br.com.api.entidades.Endereco;
import br.com.api.entidades.Pessoa;
import br.com.api.repositorios.EnderecoRepository;
import br.com.api.repositorios.PessoaRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
public class EnderecoControllerTest {

	private String uri = "/api/enderecos";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@BeforeEach
	void setup() {
		Pessoa pessoa = new Pessoa("João Roberto", LocalDate.of(1995, 05, 25));
		pessoaRepository.save(pessoa);
		Endereco endereco1 = new Endereco("Rua Manaus", "93410-300", 345, "Novo Hamburgo", pessoa);
		enderecoRepository.save(endereco1);
		Endereco endereco2 = new Endereco("Rua Coritiba", "93410-380", 678, "Novo Hamburgo", pessoa);
		enderecoRepository.save(endereco2);
	}

	@Test
	void naoDeveCriarEnderecoComLogradouroEmBranco() throws Exception {
		String enderecoDTO = "{\"logradouro\": \"\", \"cep\": \"93410-300\", \"numero\": 345, \"cidade\": \"Novo Hamburgo\", \"idPessoa\": 1}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(enderecoDTO).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void naoDeveCriarEnderecoComCepEmBranco() throws Exception {
		String enderecoDTO = "{\"logradouro\": \"Rua Manaus\", \"cep\": \"\", \"numero\": 345, \"cidade\": \"Novo Hamburgo\", \"idPessoa\": 1}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(enderecoDTO).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void naoDeveCriarEnderecoComNumeroEmBranco() throws Exception {
		String enderecoDTO = "{\"logradouro\": \"Rua Manaus\", \"cep\": \"93410-300\", \"numero\": , \"cidade\": \"Novo Hamburgo\", \"idPessoa\": 1}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(enderecoDTO).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void naoDeveCriarEnderecoComIdPessoaEmBranco() throws Exception {
		String enderecoDTO = "{\"logradouro\": \"Rua Manaus\", \"cep\": \"93410-300\", \"numero\": 345, \"cidade\": \"Novo Hamburgo\", \"idPessoa\": }";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(enderecoDTO).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void naoDeveCriarEnderecoComIdPessoaInexistente() throws Exception {
		String enderecoDTO = "{\"logradouro\": \"Rua Manaus\", \"cep\": \"93410-300\", \"numero\": 345, \"cidade\": \"Novo Hamburgo\", \"idPessoa\": 35}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(enderecoDTO).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void deveCriarEnderecoQuandoEstaTudoCerto() throws Exception {
		String enderecoDTO = "{\"logradouro\": \"Rua Manaus\", \"cep\": \"93410-300\", \"numero\": 345, \"cidade\": \"Novo Hamburgo\", \"idPessoa\": 1}";

		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(enderecoDTO).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void deveRetornarEnderecoDePessoaPorId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(uri + "/1")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void naoDeveColocarEnderecoDePessoaComoPrincipalComIdPessoaEmBranco() throws Exception {
		String enderecoDTO = "{\"idPessoa\": , \"idEndereco\": 2}";

		mockMvc.perform(MockMvcRequestBuilders.patch(uri).content(enderecoDTO).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void naoDeveColocarEnderecoDePessoaComoPrincipalComIdPessoaInexistente() throws Exception {
		String enderecoDTO = "{\"idPessoa\": 35, \"idEndereco\": 2}";

		mockMvc.perform(MockMvcRequestBuilders.patch(uri).content(enderecoDTO).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void naoDeveColocarEnderecoDePessoaComoPrincipalComIdEnderecoEmBranco() throws Exception {
		String enderecoDTO = "{\"idPessoa\": 1, \"idEndereco\": }";

		mockMvc.perform(MockMvcRequestBuilders.patch(uri).content(enderecoDTO).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void naoDeveColocarEnderecoDePessoaComoPrincipalComIdEnderecoInexistente() throws Exception {
		String enderecoDTO = "{\"idPessoa\": 1, \"idEndereco\": 37}";

		mockMvc.perform(MockMvcRequestBuilders.patch(uri).content(enderecoDTO).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}