package br.org.serratec.backend.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.dto.CategoriaDTO;
import br.org.serratec.backend.dto.CategoriaInserirDTO;

import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.model.Categoria;
import br.org.serratec.backend.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List <CategoriaDTO> listar() {
		
		List <Categoria> categorias = categoriaRepository.findAll();

		return categorias.stream().map(ca -> new CategoriaDTO(ca)).collect(Collectors.toList());
	}
	
	public CategoriaDTO inserir(CategoriaInserirDTO categoriaInserirDTO) throws EmailException {
		
		//String nome = categoriaRepository.findByNome(categoriaInserirDTO.getNome());

		if (categoriaRepository.findByNome(categoriaInserirDTO.getNome())!= null)
			throw new EmailException("Categoria  já existe! /nInsira outra");
		
		
		Categoria categorias = new Categoria();
		categorias.setNome(categoriaInserirDTO.getNome());
		categorias.setDescricao(categoriaInserirDTO.getNome());
        categoriaRepository.save(categorias);
        return new CategoriaDTO(categorias);
	}
}
