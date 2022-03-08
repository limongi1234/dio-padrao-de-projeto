package br.org.serratec.backend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Long id;
	
	@Column(name = "valorTotal")
	private double valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "cliente_pedido")
	private Cliente id_cliente;
	
	@Column
	private boolean status = false;
	
	@Column(name = "data_abertura_pedido")
	private LocalDate dataAbertura;
	
	@Column(name = "dt_envio")
	private LocalDate dt_envio;
	
	@Column(name = "dt_entrega")
	private LocalDate dt_entrega;
	
	@Column(name = "data_fechamento_pedido")
	private LocalDate dataFechamento;
	
	public Pedido() {
		super();		
	}

	public Pedido(Long id, double valorTotal, Cliente id_cliente, boolean status, LocalDate dataAbertura,
			LocalDate dt_envio, LocalDate dt_entrega, LocalDate dataFechamento) {
		super();
		this.id = id;
		this.valorTotal = valorTotal;
		this.id_cliente = id_cliente;
		this.status = status;
		this.dataAbertura = dataAbertura;
		this.dt_envio = dt_envio;
		this.dt_entrega = dt_entrega;
		this.dataFechamento = dataFechamento;
	}
	
	public LocalDate getDt_envio() {
		return dt_envio;
	}

	public void setDt_envio(LocalDate dt_envio) {
		this.dt_envio = dt_envio;
	}

	public LocalDate getDt_entrega() {
		return dt_entrega;
	}

	public void setDt_entrega(LocalDate dt_entrega) {
		this.dt_entrega = dt_entrega;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Cliente id_cliente) {
		this.id_cliente = id_cliente;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
}