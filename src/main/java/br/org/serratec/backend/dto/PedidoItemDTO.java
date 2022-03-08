package br.org.serratec.backend.dto;

import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.model.PedidoItem;
import br.org.serratec.backend.model.Produto;

public class PedidoItemDTO {
	private int quantidade;
	private Pedido id_pedido;
	private Produto id_produto;
	
	public PedidoItemDTO() {
		super();
	}
	
	public PedidoItemDTO(PedidoItem pedidoitem) {
		super();
		this.quantidade = pedidoitem.getQuantidade();
		this.id_pedido = pedidoitem.getId_pedido();
		this.id_produto = pedidoitem.getId_produto();
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Pedido id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Produto getId_produto() {
		return id_produto;
	}

	public void setId_produto(Produto id_produto) {
		this.id_produto = id_produto;
	}
}

