package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Endereco;

public interface EnderecoRepository extends JpaRepository <Endereco, Long>{
	public Endereco findByCep(String cep);
}
