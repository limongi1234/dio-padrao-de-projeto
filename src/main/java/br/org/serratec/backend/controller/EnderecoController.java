package br.org.serratec.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.dto.EnderecoDTO;
import br.org.serratec.backend.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	
	@GetMapping("/{cep}")
	@ApiOperation(value = "Buscar um CEP", notes = "Busca por CEP")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um CEP"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	public ResponseEntity <EnderecoDTO> buscar(@PathVariable String cep){
		EnderecoDTO enderecoDTO = enderecoService.buscar(cep);
		
		if(enderecoDTO == null) 
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(enderecoDTO);
	}

}
