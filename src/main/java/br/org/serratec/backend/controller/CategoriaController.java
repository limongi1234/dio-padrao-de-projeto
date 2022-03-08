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

import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.repository.CategoriaRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping ("/categorias")
public class CategoriaController 
{
     
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
	@ApiOperation (value = "Listar todas as Categorias", notes = "Listagem de Categorias")
	@ApiResponses (value = {
			@ApiResponse (code = 200, message = "Retorna todos os produtos"),
			@ApiResponse (code = 401, message = "Erro de autenticação"),
			@ApiResponse (code = 403, message = "Recurso proibido"),
			@ApiResponse (code = 404, message = "Recurso não encontrado"),
			@ApiResponse (code = 500, message = "Erro no servidor")
	})
    public List <Categoria> listar()
    {
        return categoriaRepository.findAll();
    }
    
    @GetMapping ("/{id}")
	public ResponseEntity <Categoria> buscar (@PathVariable Long id) 
    {
		Optional <Categoria> categoria = categoriaRepository.findById (id);
		if (categoria.isPresent()) 
			return ResponseEntity.ok(categoria.get());
		return ResponseEntity.notFound().build();
	}
    
    @PostMapping
	@ApiOperation (value = "Cadastrar Categorias", notes = "Cadastro de Categorias")
	@ApiResponses (value = {
			@ApiResponse (code = 200, message = "Retorna todas as categoria"),
			@ApiResponse (code = 401, message = "Erro de autenticação"),
			@ApiResponse (code = 403, message = "Recurso proibido"),
			@ApiResponse (code = 404, message = "Recurso não encontrado"),
			@ApiResponse (code = 500, message = "Erro no servidor")
	})
	public Categoria inserir (@Valid @RequestBody Categoria categoria) 
    {
		return categoriaRepository.save (categoria);
	}
    
    @PutMapping ("/{id}")
	public ResponseEntity <Categoria> alterar (@PathVariable Long id,@Valid @RequestBody Categoria categoria)
    {
		if (!categoriaRepository.existsById (id)) 
			return ResponseEntity.notFound().build();
		categoria.setId (id);
		categoria = categoriaRepository.save (categoria);
		return ResponseEntity.ok (categoria);
	}
    
    @DeleteMapping("/{id}")
	public ResponseEntity <Void> excluir (@PathVariable Long id) 
    {
		if (!categoriaRepository.existsById (id)) 
			return ResponseEntity.notFound().build();
		categoriaRepository.deleteById (id);
		return ResponseEntity.noContent().build();
	}
}

