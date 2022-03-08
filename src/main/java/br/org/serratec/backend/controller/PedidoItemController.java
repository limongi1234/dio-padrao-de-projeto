package br.org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.dto.PedidoItemDTO;
import br.org.serratec.backend.model.PedidoItem;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.PedidoItemRepository;
import br.org.serratec.backend.repository.ProdutoRepository;
import br.org.serratec.backend.service.PedidoItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/itens_pedidos")
public class PedidoItemController {
	
	
	@Autowired
    public PedidoItemRepository pedidoItemRepository;
    
    @Autowired
    public PedidoItemService pedidoItemService;
    
    @Autowired
    public ProdutoRepository produtoRepository;

    @GetMapping
	@ApiOperation(value = "Listar todos os Itens de todos os Pedidos", notes = "Listagem de Itens de Pedidos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os itens"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
    public List <PedidoItem> listar()
    {
        return pedidoItemRepository.findAll();
    }
    
    @GetMapping ("/{id}")
	public ResponseEntity <PedidoItem> buscar (@PathVariable Long id) 
    {
		Optional<PedidoItem> pedidoItem = pedidoItemRepository.findById(id);
		if (!pedidoItem.isPresent()) 
			return ResponseEntity.notFound().build();
		else 
			return ResponseEntity.ok(pedidoItem.get());
	}
  
    
    @PostMapping
	@ApiOperation(value = "Cadastrar item de pedido", notes = "Cadastro de Item de Pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o itens do pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
	public ResponseEntity <PedidoItemDTO> inserir (@Valid @RequestBody PedidoItemDTO pedidoItem) 
    {
    	Optional<Produto> prod = produtoRepository.findById(pedidoItem.getId_produto().getId());
		  if(pedidoItem.getQuantidade() > prod.get().getQuantidade_estoque()) 
			  return ResponseEntity.notFound().build();
		  PedidoItemDTO pedidoItemDTO = pedidoItemService.inserir(pedidoItem);
		  return ResponseEntity.ok(pedidoItemDTO);
		
	}
    
    @PutMapping ("/{id}")
	public ResponseEntity <PedidoItem> alterar (@PathVariable Long id,@Valid @RequestBody PedidoItem pedidoItem)
    {
		if (!pedidoItemRepository.existsById (id)) 
			return ResponseEntity.notFound().build();
		pedidoItem.setId (id);
		pedidoItem = pedidoItemRepository.save (pedidoItem);
		return ResponseEntity.ok (pedidoItem);
	}
}
