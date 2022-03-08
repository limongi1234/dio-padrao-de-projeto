package br.org.serratec.backend.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;

import com.sun.istack.NotNull;

import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Pedido;

public class PedidoInserirDTO {
	private Long id;
	
	@NotNull
	@Future(message = "insira uma data válida!")
	private LocalDate data_abertura;
	
	@NotNull
	private Cliente id_cliente;
	
	public PedidoInserirDTO(Long id, LocalDate data_abertura, Cliente id_cliente) {
		super();
		this.id = id;
		this.data_abertura = data_abertura;
		this.id_cliente = id_cliente;
	}
    
	public PedidoInserirDTO() {
		super();
	}
	
	public PedidoInserirDTO(Pedido pedido) {
		super();
		this.id = pedido.getId();
		this.data_abertura = pedido.getDataAbertura();
		this.id_cliente = pedido.getId_cliente();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public LocalDate getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(LocalDate data_abertura) {
		this.data_abertura = data_abertura;
	}

	public Cliente getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Cliente id_cliente) {
		this.id_cliente = id_cliente;
	}
}
