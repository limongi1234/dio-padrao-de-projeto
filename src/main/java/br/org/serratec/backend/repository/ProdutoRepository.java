package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> 
{
    Produto findByCodigo(Long codigo);
	
}

