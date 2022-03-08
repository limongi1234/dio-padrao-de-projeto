package br.org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.ProdutoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping ("/produtos")
public class ProdutoController 
{
     
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
	@ApiOperation(value = "Listar todos os Produtos", notes = "Listagem de Produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os produtos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Recurso proibido"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no servidor")
	})
    public List <Produto> listar()
    {
        return produtoRepository.findAll();
    }
    
    @GetMapping ("/{id}")
	public ResponseEntity <Produto> buscar (@PathVariable Long id) 
    {
		Optional <Produto> produto = produtoRepository.findById (id);
		if (produto.isPresent()) 
			return ResponseEntity.ok(produto.get());
		return ResponseEntity.notFound().build();
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
	public Produto inserir (@Valid @RequestBody Produto produto) 
    {
		return produtoRepository.save (produto);
	}
    
    @PutMapping ("/{id}")
	public ResponseEntity <Produto> alterar (@PathVariable Long id,@Valid @RequestBody Produto produto)
    {
		if (!produtoRepository.existsById (id)) 
			return ResponseEntity.notFound().build();
		produto.setId (id);
		produto = produtoRepository.save (produto);
		return ResponseEntity.ok (produto);
	}
    
    @DeleteMapping("/{id}")
	public ResponseEntity <Void> excluir (@PathVariable Long id) 
    {
		if (!produtoRepository.existsById (id)) 
			return ResponseEntity.notFound().build();
		produtoRepository.deleteById (id);
		return ResponseEntity.noContent().build();
	}
}
