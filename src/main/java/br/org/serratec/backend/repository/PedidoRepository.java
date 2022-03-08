package br.org.serratec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.model.Pedido;

public interface PedidoRepository extends JpaRepository <Pedido, Long>{

}
