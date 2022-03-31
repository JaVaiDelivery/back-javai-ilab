package br.com.javai.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.javai.projeto.dao.EntregadorDAO;
import br.com.javai.projeto.dao.PedidoDAO;
import br.com.javai.projeto.model.Entregador;
import br.com.javai.projeto.model.Pedido;
import br.com.javai.projeto.util.StatusDoPedido;

@Component
public class PedidoServiceImpl implements IPedidoService {
	
	@Autowired
	private PedidoDAO daoPedido;
	
	@Autowired
	private EntregadorDAO daoEntregador;

	@Override
	public List<Pedido> buscarPedidosEmAberto() {
		List<Pedido> pedidos = daoPedido.recuperarPedidosEmAberto();
		return pedidos;
	}

	@Override
	public Optional<Pedido> buscarPedidoPorId(Integer id) {
		Optional<Pedido> pedido = daoPedido.findById(id);
		
		if (pedido.isEmpty()) {
			return null;
		}
		
		return pedido;
	}

	@Override
	public boolean alterarPedido(Pedido pedido, Integer id) {
	
		if (pedido.getEntregador() != null) {
			
			Optional<Entregador> entregadorEncontrado = daoEntregador.findById(pedido.getEntregador().getId());
			Optional<Pedido> pedidoEncontrado = daoPedido.findById(id);
			
			if (entregadorEncontrado.isEmpty()) {
				return false;
			}
			
			if (pedidoEncontrado.isEmpty()) {
				return false;
			}
			
			daoPedido.atribuirEntregador(pedido.getEntregador().getId(), id);
		} else {
			daoPedido.removerEntregador(id);
		}
		
		daoPedido.mudarStatus(StatusDoPedido.getStatusDoPedidoValueFromInt(pedido.getStatus().getNumeroStatus()), id);
		
		return true;
	}

}