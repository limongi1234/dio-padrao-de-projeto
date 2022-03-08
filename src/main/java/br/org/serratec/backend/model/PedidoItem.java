package br.org.serratec.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PedidoItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_pedidoitem")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name= "id_produto")
	private Produto id_produto;
	
	@ManyToOne
	@JoinColumn(name= "id_pedido")
	private Pedido id_pedido;
	
	@Column(name ="vl_de_venda")
	private float vl_de_venda;
	
	@Column
	private int quantidade;
	
	public Pedido getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Pedido id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Produto getId_produto() {
		return id_produto;
	}

	public void setId_produto(Produto id_produto) {
		this.id_produto = id_produto;
	}

	public float getVl_de_venda() {
		return vl_de_venda;
	}

	public void setVl_de_venda(float vl_de_venda) {
		this.vl_de_venda = vl_de_venda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public PedidoItem() {
		super();
	}
	
	public PedidoItem(Long id_peditem, Produto id_produto, Long codigo, float vl_de_venda, int quantidade) {
		super();
		this.id_produto = id_produto;
		this.vl_de_venda = vl_de_venda;
		this.quantidade = quantidade;
	}
}	
