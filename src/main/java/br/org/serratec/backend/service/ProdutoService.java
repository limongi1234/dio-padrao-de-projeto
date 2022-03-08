package br.org.serratec.backend.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.config.MailConfig;

import br.org.serratec.backend.dto.ProdutoDTO;
import br.org.serratec.backend.dto.ProdutoInserirDTO;
import br.org.serratec.backend.exception.ProductCodeException;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private MailConfig mailconfig;
	
	public List <ProdutoDTO> listar() {
		
		List <Produto> produtos = produtoRepository.findAll();

		return produtos.stream().map(p -> new ProdutoDTO(p)).collect(Collectors.toList());
	}
	
	public ProdutoDTO inserir(ProdutoInserirDTO produtoInserirDTO) throws ProductCodeException {
		 
		if (produtoRepository.findByCodigo(produtoInserirDTO.getCodigo()) != null) 
			throw new ProductCodeException("Produto já existe ! Insira outro");
		
		Produto produto = new Produto();
		produto.setNome(produtoInserirDTO.getNome());
		produto.setDescricao(produtoInserirDTO.getDescricao());
		produto.setCategoria(produtoInserirDTO.getCategoria());
		produto.setCodigo(produtoInserirDTO.getCodigo());
		produto.setValor_unitario(produtoInserirDTO.getValor_unitario());
		produtoRepository.save(produto);
		mailconfig.enviarEmail(produto.getNome(), "Cadastro de Produto Confirmado", produto.toString());
		return new ProdutoDTO(produto);
	}
}
