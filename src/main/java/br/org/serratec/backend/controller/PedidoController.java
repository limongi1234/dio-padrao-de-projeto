package br.org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

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

import br.org.serratec.backend.dto.PedidoInserirDTO;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.repository.PedidoRepository;
import br.org.serratec.backend.service.PedidoItemService;
import br.org.serratec.backend.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping ("/pedidos")
public class PedidoController 
{
     
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private PedidoItemService pedidoItemService;
    
    @GetMapping
	@ApiOperation(value = "Listar todos os Pedidos", notes = "Listagem de Pedidos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os pedidos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
    public List <Pedido> listar()
    {
        return pedidoRepository.findAll();
    }
    
    @GetMapping ("/{id}")
	public ResponseEntity <Pedido> buscar (@PathVariable Long id) 
    {
		Optional <Pedido> pedido = pedidoRepository.findById(id);
		if (pedido.isPresent()) 
			return ResponseEntity.ok(pedido.get());
		return ResponseEntity.notFound().build();
	}
    
    @PostMapping
	@ApiOperation(value = "Cadastrar Pedidos", notes = "Cadastro de Pedidos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os pedidos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity <PedidoInserirDTO> inserir(@Valid @RequestBody PedidoInserirDTO pedidoInserirDTO)
    {	
     	 PedidoInserirDTO pedido = pedidoService.inserir(pedidoInserirDTO);
     	 return ResponseEntity.ok(pedido);		
 	}
	
    @PutMapping ("/{id}")
	public ResponseEntity <Pedido> alterar (@PathVariable Long id,@Valid @RequestBody Pedido pedido)
    {
		if (!pedidoRepository.existsById (id)) 
			return ResponseEntity.notFound().build();
		pedido.setId (id);
		pedido = pedidoRepository.save (pedido);
		return ResponseEntity.ok (pedido);
	}
    
    @PutMapping ("/fechar/{id}")
    public ResponseEntity <Pedido> fecharPedido(@PathVariable Long id){
    	if(!pedidoRepository.existsById(id)) 
    		return ResponseEntity.notFound().build();
    	else {
    		
    		Pedido pedido = pedidoService.fecharPedido(id, pedidoItemService.somarItensDoPedido(pedidoItemService.buscarPorPedido(id)));
    					pedidoItemService.descontaEstoque(pedidoItemService.buscarPorPedido(id));
    	    return ResponseEntity.ok(pedido);
    	}
    }
    
    @DeleteMapping("/{id}")
	public ResponseEntity <Void> excluir (@PathVariable Long id) 
    {
		if (!pedidoRepository.existsById (id)) 
			return ResponseEntity.notFound().build();
		pedidoRepository.deleteById (id);
		return ResponseEntity.noContent().build();
	}
    
      @PutMapping ("/enviar/{id}")
    public ResponseEntity <Pedido> enviarPedido(@PathVariable Long id){
    	if(!pedidoRepository.existsById(id)) 
    		return ResponseEntity.notFound().build();
    	else {
    		Pedido pedido = pedidoService.enviarPedido(id);
    		return ResponseEntity.ok(pedido);
    	}	
    }
}
