package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria, Long>{
	public String findByNome(String nome);
}
