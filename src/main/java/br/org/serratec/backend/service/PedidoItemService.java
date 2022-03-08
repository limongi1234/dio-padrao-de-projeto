package br.org.serratec.backend.service;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.config.MailConfig;
import br.org.serratec.backend.dto.PedidoItemDTO;
import br.org.serratec.backend.exception.ProductCodeException;
import br.org.serratec.backend.model.PedidoItem;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.PedidoItemRepository;
import br.org.serratec.backend.repository.ProdutoRepository;

@Service
public class PedidoItemService {
	
	@Autowired
	private PedidoItemRepository pedidoItemRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	private MailConfig mailconfig;
	
	public List <PedidoItemDTO> listar() {
		
		List <PedidoItem> pedidoItens = pedidoItemRepository.findAll();

		return pedidoItens.stream().map(ped -> new PedidoItemDTO(ped)).collect(Collectors.toList());
	}
	
	public PedidoItemDTO inserir(PedidoItemDTO pedidoItemDTO) throws ProductCodeException {
		 
		PedidoItem pedidoItem = new PedidoItem();
		
		pedidoItem.setId_pedido(pedidoItemDTO.getId_pedido());
		pedidoItem.setId_produto(pedidoItemDTO.getId_produto());
		pedidoItem.setQuantidade(pedidoItemDTO.getQuantidade());
		pedidoItem.setVl_de_venda(pedidoItemDTO.getId_produto().getValor_unitario());
		pedidoItemRepository.save(pedidoItem);
		
		 return new PedidoItemDTO(pedidoItem);
	}
	
	public List<PedidoItem> buscarPorPedido(Long id_pedido){
		List<PedidoItem> itensDoPedido = new ArrayList<PedidoItem>();
		List<PedidoItem> todosOsItens = pedidoItemRepository.findAll();
		PedidoItem item = new PedidoItem();
		
		for (int i=0; i<todosOsItens.size(); i++ ) {
			if(todosOsItens.get(i).getId_pedido().getId() == id_pedido) {
				item.setId_pedido(todosOsItens.get(i).getId_pedido());
				item.setId_produto(todosOsItens.get(i).getId_produto());
				item.setQuantidade(todosOsItens.get(i).getQuantidade());	
				item.setVl_de_venda(todosOsItens.get(i).getVl_de_venda());
				itensDoPedido.add(item);	
			}	
		}
		return itensDoPedido;
	}
	
	public float somarItensDoPedido(List<PedidoItem> listaItensPedido) {
		float valorDoPedido = 0;
		for(int i=0; i< listaItensPedido.size(); i++) {
			valorDoPedido += (listaItensPedido.get(i).getVl_de_venda() * listaItensPedido.get(i).getQuantidade());
		}
		return valorDoPedido;
	}
	
	public void descontaEstoque(List<PedidoItem> listaItensPedidoDescontaEstoque) {
		
		for(int i =0; i<listaItensPedidoDescontaEstoque.size(); i++) 
		{	
			Produto produto = new Produto();
			
			produto.setId(listaItensPedidoDescontaEstoque.get(i).getId_produto().getId());
			produto.setCategoria(listaItensPedidoDescontaEstoque.get(i).getId_produto().getCategoria());
			produto.setCodigo(listaItensPedidoDescontaEstoque.get(i).getId_produto().getCodigo());
			produto.setData_Cadastro(listaItensPedidoDescontaEstoque.get(i).getId_produto().getData_Cadastro());
			produto.setDescricao(listaItensPedidoDescontaEstoque.get(i).getId_produto().getDescricao());
			produto.setUri(listaItensPedidoDescontaEstoque.get(i).getId_produto().getUri());
			produto.setValor_unitario(listaItensPedidoDescontaEstoque.get(i).getId_produto().getValor_unitario());
			
			produto.setQuantidade_estoque(listaItensPedidoDescontaEstoque.get(i).getId_produto().getQuantidade_estoque() - listaItensPedidoDescontaEstoque.get(i).getQuantidade());
			produto = produtoRepository.save(produto);
		}
	}
}	

