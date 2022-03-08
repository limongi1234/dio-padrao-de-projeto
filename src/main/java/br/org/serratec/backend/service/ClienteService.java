package br.org.serratec.backend.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.config.MailConfig;
import br.org.serratec.backend.dto.ClienteDTO;
import br.org.serratec.backend.dto.ClienteInserirDTO;
import br.org.serratec.backend.dto.EnderecoDTO;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;
import br.org.serratec.backend.repository.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MailConfig mailconfig;
	
	@Autowired 
	private EnderecoService enderecoService;
	
	public List <ClienteDTO> listar() {
		
		List <Cliente> clientes = clienteRepository.findAll();
		
		return clientes.stream().map(c -> new ClienteDTO(c)).collect(Collectors.toList());
	}
	
	public ClienteDTO inserir(ClienteInserirDTO clienteInserirDTO) throws EmailException {

		if (clienteRepository.findByEmail(clienteInserirDTO.getEmail()) != null) 
			throw new EmailException("Email já existe ! Insira outro");
		
		Cliente cliente = new Cliente();
		cliente.setNome_usuario(clienteInserirDTO.getNome_usuario());
		cliente.setEmail(clienteInserirDTO.getEmail());
		EnderecoDTO endereco = enderecoService.buscar(clienteInserirDTO.getEndereco().getCep());
		Endereco ende = new Endereco();
		ende.setBairro(endereco.getBairro());
		ende.setCep(endereco.getCep());
		ende.setLocalidade(endereco.getLocalidade());
		ende.setLogradouro(endereco.getLogradouro());
		ende.setUf(endereco.getUf());
		
		cliente.setEndereco(ende);

		cliente.setSenha(bCryptPasswordEncoder.encode(clienteInserirDTO.getSenha()));
		clienteRepository.save(cliente);
		mailconfig.enviarEmail(cliente.getEmail(), "Cadastro de Cliente Confirmado", cliente.toString());
		 return new ClienteDTO(cliente);
	}
}
