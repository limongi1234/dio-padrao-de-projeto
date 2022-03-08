package br.org.serratec.backend.service;

import java.time.LocalDate;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.config.MailConfig;

import br.org.serratec.backend.dto.PedidoInserirDTO;
import br.org.serratec.backend.exception.ProductCodeException;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private MailConfig mailconfig;
	
	public List <PedidoInserirDTO> listar() {
		
		List<Pedido> pedidos = pedidoRepository.findAll();

		return pedidos.stream().map(pe -> new PedidoInserirDTO(pe)).collect(Collectors.toList());
	}
	
	public PedidoInserirDTO inserir(PedidoInserirDTO pedidoInserirDTO) throws ProductCodeException {
			 
			if (pedidoRepository.findById(pedidoInserirDTO.getId()) != null) 
				throw new ProductCodeException("Pedido já existe ! Insira outro");
			else {
				Pedido pedido = new Pedido();
			
			pedido.setId_cliente(pedidoInserirDTO.getId_cliente());
			pedido.setDataAbertura(pedidoInserirDTO.getData_abertura());
		
			pedidoRepository.save(pedido);
			
			//mailconfig.enviarEmail(produto.getNome(), "Abertura de Pedido Confirmada", pedido.toString());
			 return new PedidoInserirDTO(pedido);
		}
	}
		
	 public Pedido fecharPedido(Long id, float valor_total){
	    	
	    	if (!pedidoRepository.existsById (id)) 
	    		throw new ProductCodeException("Pedido não existe ! Insira outro");
	    	else {
	    		Pedido pedido = new Pedido();
	    		
	    		pedido.setId (id);
	    		pedido.setDataAbertura(pedidoRepository.getById(id).getDataAbertura());
	    		pedido.setId(pedidoRepository.getById(id).getId());
	    	    pedido.setId_cliente(pedidoRepository.getById(id).getId_cliente());
	    		pedido.setStatus(true);
	    		pedido.setValorTotal(valor_total);
	    		pedido.setDataFechamento(LocalDate.now());
				pedido = pedidoRepository.save (pedido);

				return pedido;
	    	}
	    }
	 
	      public Pedido enviarPedido(Long id){
	    	
	    	if (!pedidoRepository.existsById (id)) 
	    		throw new ProductCodeException("Pedido não existe ! Insira outro");
	    	else {
	    		
	    		Pedido pedido = new Pedido();
	    		if(pedidoRepository.getById(id).getStatus() == true) {
	    				
	    		pedido.setId (id);
	    		pedido.setDataAbertura(pedidoRepository.getById(id).getDataAbertura());
	    		pedido.setId(pedidoRepository.getById(id).getId());
	    	    pedido.setId_cliente(pedidoRepository.getById(id).getId_cliente());
	    		pedido.setStatus(pedidoRepository.getById(id).getStatus());
	    		pedido.setValorTotal(pedidoRepository.getById(id).getValorTotal());
	    		pedido.setDataFechamento(pedidoRepository.getById(id).getDataFechamento());
	    		pedido.setDt_envio(LocalDate.now());
	    		pedido.setDt_entrega(LocalDate.now().plusDays(20));
				pedido = pedidoRepository.save (pedido);

				return pedido;
	    		}else 
	    			throw new ProductCodeException("O pedido precisa ser finalizado!");
	    		
	    	}
	  }
}
