package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Foto;

public interface FotoRepository extends JpaRepository <Foto, Long>
{

}