package br.org.serratec.backend.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.org.serratec.backend.dto.ClienteDTO;
import br.org.serratec.backend.dto.ClienteInserirDTO;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.repository.ClienteRepository;
import br.org.serratec.backend.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	@ApiOperation(value = "Listar todos os Clientes", notes = "Listagem de Clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os clientes"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	public ResponseEntity <List<ClienteDTO>> listar(){
		return ResponseEntity.ok(clienteService.listar());
	}
	
	@PostMapping
	@ApiOperation(value = "Cadastrar Clientes", notes = "Cadastro de Clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os clientes"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity <Object> inserir(@RequestBody ClienteInserirDTO clienteInserirDTO) {
		try {
			ClienteDTO clienteDTO = clienteService.inserir(clienteInserirDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(clienteDTO.getId())
					.toUri();
			return ResponseEntity.created(uri).body(clienteDTO);
		} catch (EmailException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@PutMapping ("/{id}")
	public ResponseEntity <Cliente> alterar (@PathVariable Long id,@Valid @RequestBody Cliente cliente)
    {
		if (!clienteRepository.existsById (id)) 
			return ResponseEntity.notFound().build();
		cliente.setId (id);
		cliente = clienteRepository.save (cliente);
		return ResponseEntity.ok (cliente);
	}
    
    @DeleteMapping("/{id}")
	public ResponseEntity <Void> excluir (@PathVariable Long id) 
    {
		if (!clienteRepository.existsById (id)) 
			return ResponseEntity.notFound().build();
		clienteRepository.deleteById (id);
		return ResponseEntity.noContent().build();
	}
}

