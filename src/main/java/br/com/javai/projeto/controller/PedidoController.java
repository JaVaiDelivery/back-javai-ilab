package br.com.javai.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.javai.projeto.dao.PedidoDAO;
import br.com.javai.projeto.model.Pedido;

@RestController
public class PedidoController {

	@Autowired
	private PedidoDAO dao;
	
	@GetMapping("/pedidos")
	public List<Pedido> recuperarPedidosEmAberto() {
		return dao.recuperarPedidosEmAberto();
	}
	
	@GetMapping("/pedidos/{id}")
	public List<Pedido> recuperarPedidoEspecifico(@PathVariable int id) {
		return dao.recuperarPedidoEspecifico(id);
	}
	
}