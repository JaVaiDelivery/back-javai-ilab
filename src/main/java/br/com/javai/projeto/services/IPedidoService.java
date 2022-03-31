package br.com.javai.projeto.services;

import java.util.List;
import java.util.Optional;

import br.com.javai.projeto.model.Pedido;

public interface IPedidoService {
	public List<Pedido> buscarPedidosEmAberto();
	public Optional<Pedido> buscarPedidoPorId(Integer id);
	public boolean alterarPedido(Pedido pedido, Integer id);
}